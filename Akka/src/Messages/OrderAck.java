package Messages;

import java.io.Serializable;

public class OrderAck implements Serializable {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    boolean ordered;

    public OrderAck(String title, boolean ordered){
        this.title = title;
        this.ordered = ordered;
    }
}
