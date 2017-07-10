package Messages;

import akka.actor.ActorRef;
import akka.remote.ContainerFormats;

import java.io.Serializable;
import akka.actor.ActorRef;

public class SearchAnswer implements Serializable{
    private int price;
    private String title;

    public SearchAnswer(int price, String title){
        this.price = price;
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
