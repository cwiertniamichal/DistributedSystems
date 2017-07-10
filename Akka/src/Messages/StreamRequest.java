package Messages;

import java.io.Serializable;

public class StreamRequest implements Serializable{
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public StreamRequest(String title){
        this.title = title;
    }


}

