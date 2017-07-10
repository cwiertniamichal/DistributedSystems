package Messages;

import java.io.Serializable;

public class OrderRequest implements Serializable {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public OrderRequest(String title) {
        this.title = title;
    }
}
