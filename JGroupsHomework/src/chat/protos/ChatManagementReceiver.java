package chat.protos;

import com.google.protobuf.InvalidProtocolBufferException;
import org.jgroups.Address;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;

import org.jgroups.View;
import chat.protos.ChatOperationProtos.ChatAction;
import chat.protos.ChatOperationProtos.ChatState;
import chat.protos.ChatOperationProtos.ChatAction.ActionType;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


class ChatManagementReceiver extends ReceiverAdapter {
    private View oldView;

    @Override
    public void viewAccepted(View view){
        System.out.println("*** New view for management channel has been accepted: " + view + " ***");
        removeDisconnectedUsers(view);
    }

    @Override
    public void receive(Message msg) {
        synchronized (ChatClient.state) {
            try {
                ChatAction chatActionMessage = ChatAction.parseFrom(msg.getBuffer());
                if (chatActionMessage.getAction() == ActionType.JOIN) {
                    ChatClient.state.add(chatActionMessage);
                    ChatClient.addressToNick.put(msg.getSrc(), chatActionMessage.getNickname());
                }
                else {
                    for(ChatAction action : ChatClient.state) {
                        if(action.getNickname().equals(chatActionMessage.getNickname()) &&
                                action.getChannel().equals(chatActionMessage.getChannel())) {
                            ChatClient.state.remove(action);
                            break;
                        }
                    }
                }
            } catch (InvalidProtocolBufferException e) {
                System.out.println("Problem with parsing message: " + e.getMessage());
            }
        }
        super.receive(msg);
    }

    @Override
    public void getState(OutputStream output) throws Exception{
        ChatState.newBuilder().addAllState(ChatClient.state).build().writeTo(output);
    }

    @Override
    public void setState(InputStream input) throws Exception {
        ChatState state = ChatState.parseFrom(input);
        synchronized (ChatClient.state) {
            ChatClient.state.clear();
            ChatClient.state.addAll(state.getStateList());
        }
    }

    private void removeDisconnectedUsers(View view){
        if(oldView != null) {
            List<ChatAction> registered = new ArrayList<>();
            registered.addAll(ChatClient.state);
            for(Address address : View.leftMembers(oldView, view)){
                Iterator<ChatAction> iter = registered.iterator();
                while(iter.hasNext()){
                    ChatAction entry = iter.next();
                    if(address.toString().equals(entry.getNickname())){
                        iter.remove();
                    }
                }
            }
            ChatClient.state.clear();
            ChatClient.state.addAll(registered);
        }
        oldView = view;
    }
}
