package chat.protos;

import java.io.*;
import java.util.Collections;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.logging.Handler;
import java.util.logging.LogManager;

import org.jgroups.*;
import org.jgroups.Message;
import org.jgroups.protocols.*;
import org.jgroups.protocols.pbcast.*;
import org.jgroups.stack.Protocol;
import org.jgroups.stack.ProtocolStack;

import chat.protos.ChatOperationProtos.ChatMessage;
import chat.protos.ChatOperationProtos.ChatAction;
import chat.protos.ChatOperationProtos.ChatAction.ActionType;


public class ChatClient {
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private String nickname;

    private JChannel managementChannel;
    static final List<ChatAction> state = Collections.synchronizedList(new ArrayList<>());
    static final Map<Address, String> addressToNick = new HashMap<>();
    private Map<String, JChannel> channels = new HashMap<>();


    private static final int GET_STATE_TIMEOUT = 10000;

    public static void main(String[] args) throws Exception{
        System.setProperty("java.net.preferIPv4Stack","true");
        new ChatClient().start();
    }

    private void start() throws Exception {
        removeAnnoyingLogger();
        setNickname();


        // create management managementChannel
        managementChannel = new JChannel(false);
        managementChannel.setName(nickname);
        managementChannel.setReceiver(new ChatManagementReceiver());
        setChannelStack(managementChannel, null);
        managementChannel.connect("ChatManagement321321");
        managementChannel.getState(null, GET_STATE_TIMEOUT); // null means that we will get state from the coordinator
        eventLoop();
        try{
            Thread.sleep(500000);
        } catch (InterruptedException e){
            System.out.println("Sleep interrupted: " + e.getMessage());
        }
        managementChannel.close();
    }

    private void setNickname(){
        try{
            System.out.println("Enter nickname: ");
            nickname = in.readLine();
            System.out.println("Hello " + nickname);
            System.setProperty("user.name", nickname);
        }catch (IOException e){
            System.out.println("Error during setting nickname: " + e.getMessage());
        }
    }

    private void eventLoop(){
        JChannel currentChannel = null;
        String address = null;
        printOptions();
        while(true){
            try{
                String message = in.readLine();
                String[] options = message.split(" ");

                if(message.toLowerCase().startsWith("/info")){
                    printInfo(currentChannel, address);
                    continue;
                }
                if(message.toLowerCase().startsWith("/quit")) {
                    if(channels.containsKey(address)) {
                        leaveChannel(channels.get(options[1]), options[1]);
                        if(address != null && address.equals(options[1])){
                            currentChannel = null;
                        }
                    }
                    else{
                        System.out.println("You're not in such a channel");
                    }
                    continue;
                }
                if(message.toLowerCase().startsWith("/exit")){
                    break;
                }
                if(message.toLowerCase().startsWith("/join") || message.toLowerCase().startsWith("/new")){
                    joinChannel(options[1]);
                    continue;
                }
                if(message.toLowerCase().startsWith("/sw")){
                    address = options[1];
                    if(channels.containsKey(address))
                        currentChannel = channels.get(options[1]);
                    else{
                        System.out.println("You're not in such a channel");
                        continue;
                    }
                    System.out.println("Switched to: " + address);
                    continue;
                }
                if(message.toLowerCase().startsWith("/lsc")){
                    ChatManagementState.printCanals();
                    continue;
                }
                if(message.toLowerCase().startsWith("/lsu")){
                    ChatManagementState.printUsers();
                    continue;
                }
                if(message.toLowerCase().startsWith("/lsa")){
                    ChatManagementState.printAll();
                    continue;
                }
                if(message.toLowerCase().startsWith("/help")){
                    printOptions();
                    continue;
                }

                ChatMessage chatMessage = ChatMessage.newBuilder().setMessage(message).build();
                Message msg = new Message(null, null, chatMessage.toByteArray());
                if(channels.get(options[0]) != null)
                    channels.get(options[0]).send(msg);
                else if(currentChannel != null)
                    currentChannel.send(msg);
                else
                    System.out.println("Please specify the channel on which you want to talk");
            } catch (Exception e){
                System.out.print(e.getMessage());
            }
        }
    }

    private void printOptions(){
        System.out.println(
                "You can use following options: \n" +
                        "/quit channel_name - to exit from channel \n" +
                        "/join or /new channel_name - to enter or create new channel\n" +
                        "/lsc to print all running channels\n" +
                        "/lsu to print all users\n" +
                        "/lsa to print all channels and users chatting on them\n" +
                        "/sw channel_name - to switch to one of your's canal\n" +
                        "/exit to exit from application\n" +
                        "/help to print this menu once again\n" +
                        "/info to get information on which channel you speaking right now\n"
        );
    }

    private void printInfo(JChannel channel, String address){
        if(channel == null)
            System.out.println("*** Please choose a channel where you want to speak ***");
        else
            System.out.println("*** You're in the " + address + " channel ***");
    }

    private void setChannelStack(JChannel channel, String address){
        Protocol udp = new UDP();
        if(address != null) {
            try {
                udp.setValue("mcast_group_addr", InetAddress.getByName(address));
            } catch(UnknownHostException e){
                System.out.println("No such a host during creating UDP protocol: " + e.getMessage());
            }
        }

        ProtocolStack stack = new ProtocolStack();
        channel.setProtocolStack(stack);
        stack.addProtocol(udp)
                .addProtocol(new PING())
                .addProtocol(new MERGE3())
                .addProtocol(new FD_SOCK())
                .addProtocol(new FD_ALL().setValue("timeout", 12000).setValue("interval", 3000))
                .addProtocol(new VERIFY_SUSPECT())
                .addProtocol(new BARRIER())
                .addProtocol(new NAKACK2())
                .addProtocol(new UNICAST3())
                .addProtocol(new STABLE())
                .addProtocol(new GMS())
                .addProtocol(new UFC())
                .addProtocol(new MFC())
                .addProtocol(new FRAG2())
                .addProtocol(new STATE_TRANSFER())
                .addProtocol(new FLUSH());
        try {
            stack.init();
        } catch (Exception e){
            System.out.println("Error during initiating stack: " + e.getMessage());
        }
    }

    private void leaveChannel(JChannel channel, String address){
        // inform other users
        ChatAction chatActionMessage = ChatAction.newBuilder().setAction(ActionType.LEAVE)
                .setChannel(address).setNickname(nickname).build();
        Message msg = new Message(null, null, chatActionMessage.toByteArray());
        try {
            managementChannel.send(msg);
        }catch (Exception e){
            System.out.println("Error while sending leave message " + e.getMessage());
        }
        channels.remove(address);
        try {
            channel.close();
        } catch (NullPointerException e){
            System.out.println("Cannot leave current channel: \n" + e.getMessage());
        }
    }

    private void joinChannel(String address){
        JChannel channel;
        if (channels.containsKey(address)){
            System.out.println("You're already in that channel");
            return;
        }
        try {
            channel = new JChannel(false);
            channel.setReceiver(new ChatReceiver(address));
            channel.setName(nickname);
            setChannelStack(channel, address);
            channel.connect(address);

            // send synchronization message
            ChatAction chatActionMessage = ChatAction.newBuilder().setAction(ActionType.JOIN)
                    .setChannel(address).setNickname(nickname).build();
            Message msg = new Message(null, null, chatActionMessage.toByteArray());
            managementChannel.send(msg);
            channels.put(address, channel);
        } catch (Exception e){
            System.out.println("Error while joining new channel. More info:\n" + e.getMessage());
        }
    }

//    private String getAddressForCurrentChannel(){
//        System.out.println("Enter channel name: ");
//        try{
//            String address = in.readLine();
//            if (!channels.containsKey(address)){
//                System.out.println("You are not in such a channel");
//                return null;
//            }
//            return address;
//        } catch (IOException e){
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }

    private void removeAnnoyingLogger() {
        LogManager.getLogManager().reset();
        java.util.logging.Logger globalLogger = java.util.logging.Logger.getLogger("global");
        Handler[] handlers = globalLogger.getHandlers();
        for(Handler handler : handlers) {
            globalLogger.removeHandler(handler);
        }
    }

}