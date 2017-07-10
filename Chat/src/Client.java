import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Client implements Runnable {
    // socket input stream
    private static BufferedReader in = null;

    static Boolean closed = false;

    public static void main(String[] args) throws IOException {
        // default configuration
        String hostName = "localhost";
        int portNumber = 12345;
        InetAddress address = InetAddress.getByName(hostName);
        InetAddress[] groupAddresses = null;

        // declare sockets
        Socket socket;
        MulticastSocket socketUDP;

        PrintWriter out;
        BufferedReader stdIn;
        String message;

        // get specific configuration
        if(args.length >= 2){
            System.out.println("Using hostName: " + args[0] + " and portNumber: " + args[1]);
            hostName = args[0];
            portNumber = Integer.valueOf(args[1]);
            groupAddresses = new InetAddress[args.length - 2];
            for(int i = 0; i + 2 < args.length; i++){
                groupAddresses[i] = InetAddress.getByName(args[i + 2]);
            }
        }

        System.out.println("TCP client has started");

        try {
            // create sockets
            socket = new Socket(hostName, portNumber);
            socketUDP = new MulticastSocket();

            // join multicast groups
            if(groupAddresses != null) {
                for(InetAddress groupAddress : groupAddresses) {
                    socketUDP.joinGroup(groupAddress);
                }
            }

            // create in & out streams
            out = new PrintWriter(socket.getOutputStream(), true); // get socket output stream and open PrintWriter on it
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // get socket input stream and open BufferedReader on it
            stdIn = new BufferedReader(new InputStreamReader(System.in)); // input user message
            // Thanks to readers and writers we can use unicode characters over the server

            // start thread responsible for reading responses from server
            new Thread(new Client()).start();
            Thread t1 = new Thread(new MessageReceiverUDP(socketUDP));
            t1.start();

            // send UDP message to register client
            DatagramPacket sendPacket = new DatagramPacket(new byte[0], 0, address, portNumber);
            socketUDP.send(sendPacket);

            // get messages to send
            while(!closed){
                message = stdIn.readLine();
                out.println(message); // send message to server through TCP (needed also to determine that next message will be send using UDP)

                // send UDP message
                if(message.equals("M")){
                    byte[] sendBuffer = stdIn.readLine().getBytes();
                    sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, portNumber);
                    socketUDP.send(sendPacket);
                }

                // send UDP multicast message
                else if(message.equals("N")){
                    // get group's IP address
                    System.out.println("Enter the group IP:");
                    byte[] sendIPBuffer = (stdIn.readLine()).getBytes();
                    DatagramPacket sendIPPacket = new DatagramPacket(sendIPBuffer, sendIPBuffer.length, address, portNumber);
                    socketUDP.send(sendIPPacket);

                    // get message
                    System.out.println("Enter message:");
                    byte [] sendBuffer = (stdIn.readLine()).getBytes();
                    sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, portNumber);
                    socketUDP.send(sendPacket);
                }
            }
            stdIn.close();
            in.close();
            out.close();
            socketUDP.close();
            socket.close();

        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + hostName);
        } catch (IOException e){
            System.err.println("Couldn't get I/O for the connection to the host " + hostName);
        }
    }

    public void run(){
        String responseLine;
        try{
            while((responseLine = in.readLine()) != null){
                System.out.println(responseLine);
                if (responseLine.contains("*** Bye"))
                    break;
            }
            closed = true;
        } catch (IOException e){
            System.err.println("IOException:  " + e);
        }
    }

}

class MessageReceiverUDP extends Thread{
    private MulticastSocket socket = null;

    MessageReceiverUDP(MulticastSocket socket){
        this.socket = socket;
    }

    public void run(){
        try {
            while(!Client.closed) {
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                String msg = new String(receivePacket.getData());
                System.out.println(msg);
            }
        }catch (IOException e){
            if(!Client.closed)
                System.out.println("Sorry something wrong with UDP socket");
        }
    }
}
