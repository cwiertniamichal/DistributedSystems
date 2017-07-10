package Bookstore;

import akka.actor.AbstractActor;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;

import Messages.*;
import akka.actor.SupervisorStrategy;
import akka.japi.pf.DeciderBuilder;
import scala.concurrent.duration.Duration;
import static akka.actor.SupervisorStrategy.restart;
import static akka.actor.SupervisorStrategy.stop;
import akka.actor.*;


public class BookstoreActor extends AbstractActor {
    private ActorRef orderWorker;
    private ActorRef streamActor;

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(SearchRequest.class, sr -> {
                    context().actorOf(Props.create(SearchActor.class)).forward(sr, context());
                 })
                .match(OrderRequest.class, or -> {
                    ActorRef orderActor = context().actorOf(Props.create(OrderActor.class, getSender(), context().child("OrderWorker").get()));
                    SearchRequest rq = new SearchRequest(or.getTitle());
                    context().actorOf(Props.create(SearchActor.class)).tell(rq, orderActor);
                })
                .match(StreamRequest.class, sr -> {
                    context().child("StreamActor").get().forward(sr, context());
                })
                .matchAny(o -> System.out.println("Bookstore actor received unknown message." + o))
                .build();
    }

    @Override
    public void preStart(){
        streamActor = context().actorOf(Props.create(StreamActor.class), "StreamActor");
        orderWorker = context().actorOf(Props.create(OrderWorker.class), "OrderWorker");
    }

    private SupervisorStrategy strategy
        = new OneForOneStrategy(10, Duration.create("1 minute"), DeciderBuilder.
                matchAny(o -> {
                    if(getSender() == orderWorker || getSender() == streamActor)
                        return restart();
                    return stop();}).
                build());

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }
}
