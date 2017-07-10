package Messages;

import akka.actor.ActorRef;

import java.io.Serializable;

public class SearchRequest implements Serializable {
    private String title;

    public SearchRequest(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
