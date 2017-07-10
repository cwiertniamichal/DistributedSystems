import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

public class Admin {
    private static final String hospitalExchangeName = "Hospital";
    private static final String adminExchangeName = "Admin";

    public static void main(String[] args) throws IOException, TimeoutException{

        System.out.println("Admin has started");

        // connection and channel
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel hospitalChannel = connection.createChannel();
        Channel adminChannel = connection.createChannel();

        // exchange
        hospitalChannel.exchangeDeclare(hospitalExchangeName, BuiltinExchangeType.TOPIC);
        adminChannel.exchangeDeclare(adminExchangeName, BuiltinExchangeType.FANOUT);

        String hospitalQueueName = hospitalChannel.queueDeclare().getQueue();
        String hospitalBindingKey = "#";
        hospitalChannel.queueBind(hospitalQueueName, hospitalExchangeName, hospitalBindingKey);
        System.out.println("Joined queue " + hospitalQueueName);

        Consumer consumer = new DefaultConsumer(hospitalChannel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException{
                String message = new String(body, "UTF-8");
                System.out.println("Received: " + message);
            }
        };

        // Listening
        System.out.println("Waiting for messages...");
        hospitalChannel.basicConsume(hospitalQueueName, true, consumer);

        // reader
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            // read message nad injury type
            System.out.println("Enter info message: ");
            String infoMessage = in.readLine(); // will be used as routing key
            if(infoMessage.equals("exit"))
                break;

            adminChannel.basicPublish(adminExchangeName, "", null, (infoMessage).getBytes("UTF-8")); // should be persistent??
            System.out.println("Sent: " + infoMessage);
        }

        adminChannel.close();
        hospitalChannel.close();
        connection.close();

    }


}
