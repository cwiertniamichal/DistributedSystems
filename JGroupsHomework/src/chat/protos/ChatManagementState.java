package chat.protos;

import org.jgroups.ReceiverAdapter;
import chat.protos.ChatOperationProtos.ChatAction;

import java.util.*;


class ChatManagementState extends ReceiverAdapter {

    static void printCanals(){
        System.out.println("Running channels: ");
        Set<String> noDups = new HashSet<>();
        for (ChatAction canal: ChatClient.state) {
            if(noDups.add(canal.getChannel()))
                System.out.println(canal.getChannel());

        }
        System.out.println("");
    }

    static void printUsers(){
        System.out.println("Online users: ");
        Set<String> noDups = new HashSet<>();
        for (ChatAction actions: ChatClient.state) {
            if(noDups.add(actions.getNickname()))
                System.out.println(actions.getNickname());
        }
        System.out.println("");
    }

    static void printAll(){
        System.out.println("Running channels and users connected to them: ");
        Map<String, List<String>> canalUsers = new HashMap<>();
        for (ChatAction action: ChatClient.state) {
            if (!canalUsers.containsKey(action.getChannel()))
                canalUsers.put(action.getChannel(), new ArrayList<>());
            canalUsers.get(action.getChannel()).add(action.getNickname());
        }
        for (String key: canalUsers.keySet()) {
            System.out.println(key + " " + canalUsers.get(key));
        }
        System.out.println("");
    }
}
