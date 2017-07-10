package Bookstore;

import Messages.*;
import akka.actor.*;
import akka.japi.pf.DeciderBuilder;
import scala.concurrent.duration.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;

import static akka.actor.SupervisorStrategy.restart;

public class OrderActor extends AbstractActor {
    private ActorRef sender;
    private ActorRef worker;

    public OrderActor(ActorRef sender, ActorRef worker){
        this.sender = sender;
        this.worker = worker;
    }

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(SearchAnswer.class, sa -> {
                    if(sa.getPrice() != -1)
                        worker.tell(new OrderRequest(sa.getTitle()), sender);
                    else
                        sender.tell(new OrderAck(sa.getTitle(), false), getSelf());
                    context().stop(self());
                })
                .matchAny(o -> System.out.println("Order actor received unknown message." + o))
                .build();
    }

}
