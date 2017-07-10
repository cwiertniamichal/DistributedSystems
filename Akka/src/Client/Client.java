package Client;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {
    public static void main(String[] args){

        // config
        File configFile = new File("clientConf.conf");
        Config config = ConfigFactory.parseFile(configFile);

        // create actor system
        final ActorSystem system = ActorSystem.create("clientSystem", config);
        final ActorRef clientActor = system.actorOf(Props.create(ClientActor.class), "clientActor");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true){
                String cmd = br.readLine();
                if(cmd.startsWith("/quit")){
                    break;
                }
                clientActor.tell(cmd, null);
            }
        } catch (IOException e){
            System.out.println("Problem with IO: " + e.getMessage());
        }

        system.terminate();
    }
}
