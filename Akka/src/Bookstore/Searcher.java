package Bookstore;

import akka.actor.AbstractActor;

import java.io.*;

import Messages.*;


public class Searcher extends AbstractActor {
    private String databasePath;

    public Searcher(String databasePath){
        this.databasePath = databasePath;
    }

    @Override
        public AbstractActor.Receive createReceive() {
            return receiveBuilder()
                    .match(SearchRequest.class, rq -> {
                        int price = searchDatabase(rq.getTitle());
                        getSender().tell(new SearchAnswer(price, rq.getTitle()), getSelf());
                    })
                    .matchAny(o -> System.out.println("Searcher received unknown message"))
                    .build();
        }

    private int searchDatabase(String title) throws IOException{
        File file = new File(databasePath);
        title = title.toLowerCase();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(title.equals((line.split(", ")[0]).toLowerCase())){
                    return Integer.parseInt(line.split(", ")[1]);
                }
            }
        }
        return -1;
    }


}
