package Client;

import akka.actor.AbstractActor;
import akka.actor.FSM;
import akka.actor.Props;

import Messages.*;
import akka.actor.Status;
import akka.util.ByteString;

import java.nio.file.NoSuchFileException;


public class ClientActor extends AbstractActor {

    @Override
    public AbstractActor.Receive createReceive(){
        return receiveBuilder()
                .match(String.class, s ->{
                    if(s.startsWith("/find")){
                        String title = s.substring(6);
                        getContext().actorSelection("akka.tcp://bookstoreSystem@127.0.0.1:3553/user/bookstoreActor")
                                .tell(new SearchRequest(title), getSelf());
                    }
                    if(s.startsWith("/order")){
                        String title = s.substring(7);
                        getContext().actorSelection("akka.tcp://bookstoreSystem@127.0.0.1:3553/user/bookstoreActor")
                                .tell(new OrderRequest(title), getSelf());
                    }
                    if(s.startsWith("/stream")){
                        String title = s.substring(8);
                        getContext().actorSelection("akka.tcp://bookstoreSystem@127.0.0.1:3553/user/bookstoreActor")
                                .tell(new StreamRequest(title), getSelf());
                    }
                })
                .match(SearchAnswer.class, sa -> {
                    if(sa.getPrice() == -1){
                        System.out.println("Could not find book with title: " + sa.getTitle());
                    }
                    else {
                        System.out.println("Book with title: " + sa.getTitle() + " costs " + sa.getPrice());
                    }
                })
                .match(ByteString.class, stream ->
                    System.out.println(stream.utf8String())
                )
                .match(OrderAck.class, oAck -> {
                    if(oAck.isOrdered())
                        System.out.println("Successfully ordered book " + oAck.getTitle());
                    else
                        System.out.println("Could not order: " + oAck.getTitle() + ". There is no such a book in bookstore");
                })
                .match(CloseSystemMessage.class, mes ->{
                    System.out.println("End of file");
                })
                .match(Status.Failure.class, e ->
                    System.out.println("No such book in bookstore")
                )
                .matchAny(o -> System.out.println("Received unknown message" + o))
                .build();
    }

}
