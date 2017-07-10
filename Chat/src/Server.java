import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.concurrent.*;

public class Server {
    public static void main(String[] args){
        // default configuration
        int portNumber = 12345;
        int maxClientsCount = 10;

        ServerSocket serverSocket = null;
        MulticastSocket socketUDP = null;

        // set specific configuration
        if(args.length >= 1){
            System.out.println("Using portNumber: " + args[0]);
            portNumber = Integer.valueOf(args[0]);
            if(args.length >= 2)
                maxClientsCount = Integer.valueOf(args[1]);
        }

        ExecutorService executor = Executors.newFixedThreadPool(maxClientsCount);
        clientThread[] threads = new clientThread[maxClientsCount];
        InetAddress[] addresses = new InetAddress[maxClientsCount];
        int[] ports = new int[maxClientsCount];
        System.out.println("TCP server has started");

        try {
            // create server sockets
            serverSocket = new ServerSocket(portNumber);
            socketUDP = new MulticastSocket(portNumber);
        } catch(IOException e){
            System.out.println("Exception while creating server socket " + e.getMessage());
        }

        // accept clients
        while(true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client has connected");
                int i;
                // create thread for new client
                for (i = 0; i < maxClientsCount; i++) {
                    if(threads[i] == null){
                        clientThread worker = new clientThread(clientSocket, threads, socketUDP, addresses, ports, i);
                        threads[i] = worker;
                        executor.execute(worker);
                        break;
                    }
                }
                if(i == maxClientsCount) {
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                    out.println("Server too busy. Try later.");
                    out.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println("Exception while creating client socket " + e.getMessage());
            } catch (NullPointerException e){
                System.out.println("Exception during accepting clients " + e.getMessage());
            }
        }
    }
}


class clientThread implements Runnable {
    private BufferedReader in = null;
    private PrintWriter out = null;

    private final clientThread[] threads;
    private int maxClientsCount;
    private final InetAddress[] addresses;
    private final int[] ports;
    private final int id;

    private Socket clientSocket = null;
    private MulticastSocket socketUDP = null;

    clientThread(Socket clientSocket, clientThread[] threads, MulticastSocket socketUDP, InetAddress[] addresses, int[] ports, int id) {
        this.clientSocket = clientSocket;
        this.socketUDP = socketUDP;
        this.threads = threads;
        this.addresses = addresses;
        this.ports = ports;
        this.id = id;
        maxClientsCount = threads.length;

    }

    public void run() {
        int maxClientsCount = this.maxClientsCount;
        clientThread[] threads = this.threads;
        boolean started = true;
        try {
            //Create input and output streams for this client.
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // register client in UDP
            synchronized (this) {
                try {
                    socketUDP.setSoTimeout(1000);
                    byte[] registerBuffer = new byte[1024];
                    DatagramPacket registerPacket = new DatagramPacket(registerBuffer, registerBuffer.length);
                    socketUDP.receive(registerPacket);
                    addresses[id] = registerPacket.getAddress();
                    ports[id] = registerPacket.getPort();
                    socketUDP.setSoTimeout(0);
                }catch (SocketTimeoutException e){
                    socketUDP.setSoTimeout(0);
                    threads[id].out.println("UDP registration didn't work. Please reconnect.");
                    started = false;
                }
            }
            if(started) {
                // get name of the client
                out.println("Enter your name:");
                String name = in.readLine();

                // greet new client
                out.println("Hello " + name + " to our chat room.\nTo leave enter /quit in a new line");

                // send new user to other users
                synchronized (this) {
                    for (int i = 0; i < maxClientsCount; i++) {
                        if (threads[i] != null && threads[i] != this)
                            threads[i].out.println("*** A new user " + name + " entered the chat room !!! ***");
                    }
                }

                // get messages from client
                while (true) {
                    String line = in.readLine();
                    if (line.startsWith("/quit")) {
                        break;
                    }

                    // UDP message
                    if (line.equals("M")) {
                        byte[] receiveBuffer = new byte[1024];
                        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                        socketUDP.receive(receivePacket);

                        // send message to other clients
                        byte[] sendBuffer = ("<" + name + ">: " + new String(receiveBuffer)).getBytes();
                        synchronized (this) {
                            for (int i = 0; i < addresses.length; i++) {
                                if (addresses[i] != null && threads[i] != this) {
                                    System.out.print(addresses[i]);
                                    DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, addresses[i], ports[i]);
                                    socketUDP.send(sendPacket);
                                }
                            }
                        }
                    }

                    // UDP multicast message
                    else if (line.equals("N")) {
                        byte[] receiveIPBuffer = new byte[1024];
                        DatagramPacket receiveIPPacket = new DatagramPacket(receiveIPBuffer, receiveIPBuffer.length);
                        socketUDP.receive(receiveIPPacket);
                        String address = new String(receiveIPPacket.getData());

                        byte[] receiveBuffer = new byte[1024];
                        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                        socketUDP.receive(receivePacket);

                        // send message to other clients
                        byte[] sendBuffer = ("<" + name + ">: " + new String(receiveBuffer)).getBytes();
                        synchronized (this) {
                            for (int i = 0; i < addresses.length; i++) {
                                if (addresses[i] != null && threads[i] != this) {
                                    DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, InetAddress.getByName(address), ports[i]);
                                    socketUDP.send(sendPacket);
                                }
                            }
                        }
                    }

                    // TCP message
                    else {
                        synchronized (this) {
                            for (int i = 0; i < maxClientsCount; i++) {
                                if (threads[i] != null && threads[i] != this) {
                                    threads[i].out.println("<" + name + ">: " + line);
                                }
                            }
                        }
                    }

                }

                synchronized (this) {
                    for (int i = 0; i < maxClientsCount; i++) {
                        if (threads[i] != null && threads[i] != this) {
                            threads[i].out.println("*** The user " + name
                                    + " is leaving the chat room !!! ***");
                        }
                    }
                }

                out.println("*** Bye " + name + " ***");
            }
            // make space for new client
            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == this) {
                        threads[i] = null;
                        addresses[i] = null;
                        ports[i] = 0;
                    }
                }
            }

            // close streams
            in.close();
            out.close();
            clientSocket.close();
        }
        catch (IOException e) {
            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == this) {
                        threads[i] = null;
                        addresses[i] = null;
                        ports[i] = 0;
                    }
                }
            }
        }
    }
}








