package Bookstore;

import akka.actor.AbstractActor;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import Messages.*;

public class OrderWorker extends AbstractActor {
    private final static Path ordersFile = Paths.get("orders.txt");

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(OrderRequest.class, or -> {
                    orderBook(or.getTitle());
                    getSender().tell(new OrderAck(or.getTitle(), true), getSelf());
                })
                .matchAny(o -> System.out.println("received unknown message order actor" + o.getClass()))
                .build();
    }

    private void orderBook(String title) throws IOException{
        List<String> line = new ArrayList<>();
        line.add(title);
        Files.write(ordersFile, line, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
    }

}
