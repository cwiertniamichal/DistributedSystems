package Bookstore;

import Messages.SearchAnswer;
import Messages.SearchRequest;
import akka.actor.*;
import akka.japi.pf.DeciderBuilder;
import scala.concurrent.duration.Duration;
import static akka.actor.SupervisorStrategy.restart;
import static akka.actor.SupervisorStrategy.stop;

import java.io.FileNotFoundException;

public class SearchActor extends AbstractActor {
    private ActorRef searcher1;
    private ActorRef searcher2;
    private static final String database1 = "Books1.txt";
    private static final String database2 = "Books2.txt";
    private String title;
    private ActorRef sender;
    private boolean anotherFound = false;
    private boolean anotherStopped = false;

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(SearchRequest.class, s -> {
                    title = s.getTitle();
                    sender = getSender();

                    // create workers one for each database
                    searcher1 = context().actorOf(Props.create(Searcher.class, database1));
                    searcher2 = context().actorOf(Props.create(Searcher.class, database2));

                    // send requests to workers
                    // mes1 = new SearchRequest(s.getTitle(), getSender());
                    // mes2 = new SearchRequest(s.getTitle(), getSender());
                    searcher1.tell(new SearchRequest(s.getTitle()), getSelf());
                    searcher2.tell(new SearchRequest(s.getTitle()), getSelf());
                })
                .match(SearchAnswer.class, sa -> {
                    // if anotherFound and second worker doesn't sent a reply yet
                    if (sa.getPrice() != -1 && !anotherFound) {
                        anotherFound = true;

                        // send reply
                        sender.tell(new SearchAnswer(sa.getPrice(), sa.getTitle()), getSelf());

                        // stop both workers and search actor
                        context().stop(searcher1);
                        context().stop(searcher2);
                        context().stop(self());
                    }

                    // if not found yet and we didn't found
                    if (sa.getPrice() == -1 && !anotherFound) {

                        // second worker is already stooped
                        if (anotherStopped) {
                            sender.tell(new SearchAnswer(sa.getPrice(), sa.getTitle()), getSelf());
                            context().stop(getSender());
                            context().stop(self());
                        }

                        // second worker still looking for title
                        else {
                            context().stop(getSender());
                            anotherStopped = true;
                        }
                    }
                })
                .matchAny(o -> System.out.println("Search actor received unknown message." + o))
                .build();
    }

    private SupervisorStrategy strategy
            = new OneForOneStrategy(10, Duration.create("1 minute"), DeciderBuilder.
                    match(FileNotFoundException.class, e -> {

                        // message not sent yet
                        if (!anotherFound){
                            if (anotherStopped){
                                sender.tell(new SearchAnswer(-1, title), getSelf());
                                context().stop(getSender());
                                context().stop(self());
                            }
                            anotherStopped = true;
                        }
                        return stop();
                    }).
                    matchAny(o -> stop()).
                    build());

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

}