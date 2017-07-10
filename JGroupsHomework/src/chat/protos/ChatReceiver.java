package chat.protos;

import com.google.protobuf.InvalidProtocolBufferException;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

import chat.protos.ChatOperationProtos.ChatMessage;

class ChatReceiver extends ReceiverAdapter{
    private String channelName;

    ChatReceiver(String channelName){
        this.channelName = channelName;
    }

    public void viewAccepted(View newView){
        System.out.println("*** New view for channel " + channelName + " has been accepted: " + newView + " ***");
    }

    public void receive(Message msg){
        byte[] raw_stream = msg.getBuffer();
        try{
            ChatMessage chatMsg = ChatMessage.parseFrom(raw_stream);
            System.out.println("<" + channelName + "> " + msg.getSrc().toString() +  ": " + chatMsg.getMessage());
        } catch(InvalidProtocolBufferException e){
            System.out.println("Problem with parsing message: " + e.getMessage());
        }
    }
}
