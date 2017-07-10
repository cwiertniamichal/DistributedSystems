package Bookstore;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bookstore {
    static Materializer materializer = null;

    public static void main(String[] args){

        //config
        File configFile = new File("bookstoreConf.conf");
        Config config = ConfigFactory.parseFile(configFile);

        final ActorSystem system = ActorSystem.create("bookstoreSystem", config);
        final ActorRef bookstoreActor = system.actorOf(Props.create(BookstoreActor.class), "bookstoreActor");
        materializer = ActorMaterializer.create(system);

        // interaction
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String line = br.readLine();
                if (line.equals("q")) {
                    break;
                }
            }
        } catch (IOException e){
            System.out.println("Problem with IO: " + e.getMessage());
        }

        system.terminate();

    }
}
