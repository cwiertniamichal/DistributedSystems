import com.rabbitmq.client.*;
import com.rabbitmq.client.AMQP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Technician {
    private static List<String> injuries = new ArrayList<>();
    private static final String hospitalExchangeName = "Hospital";
    private static final String adminExchangeName = "Admin";

    public static void main(String[] args) throws IOException, TimeoutException{

        // define injuries
        injuries.add("knee");
        injuries.add("elbow");
        injuries.add("ankle");

        checkArguments(args);

        String firstQueueName = args[0];
        String secondQueueName = args[1];

        System.out.println("Technician");

        // connection and channel
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel hospitalChannel = connection.createChannel();
        Channel adminChannel = connection.createChannel();

        // exchange
        hospitalChannel.exchangeDeclare(hospitalExchangeName, BuiltinExchangeType.TOPIC);
        adminChannel.exchangeDeclare(adminExchangeName, BuiltinExchangeType.FANOUT);

        // set quality of service
        hospitalChannel.basicQos(1);

        // queue and bind
        declareAndBindQueue(hospitalChannel, firstQueueName,  hospitalExchangeName, firstQueueName, false);
        declareAndBindQueue(hospitalChannel, secondQueueName, hospitalExchangeName, secondQueueName, false);
        String adminQueueName = declareAndBindQueue(adminChannel, "", adminExchangeName, "", true);

        // message handling
        Consumer consumer = new DefaultConsumer(hospitalChannel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException{
                String message = new String(body, "UTF-8");
                System.out.println("Received: " + message);
                hospitalChannel.basicPublish(hospitalExchangeName, properties.getReplyTo(), null, (message + " test").getBytes("UTF-8"));
                hospitalChannel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        // listen for replies
        Consumer adminConsumer = new DefaultConsumer(adminChannel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException{
                String message = new String(body, "UTF-8");
                System.out.println("Received: " + message);
                // we don't want other to get this information in case of client fails
                // it's only admin info
            }
        };

        // Listening
        System.out.println("Waiting for messages...");
        hospitalChannel.basicConsume(firstQueueName, false, consumer);
        hospitalChannel.basicConsume(secondQueueName, false, consumer);
        adminChannel.basicConsume(adminQueueName, true, adminConsumer);


    }

    private static String declareAndBindQueue(Channel channel, String queueName, String exchangeName, String routingKey,
                                            boolean random)
            throws IOException {
        if(random)
            queueName = channel.queueDeclare().getQueue();
        else
            channel.queueDeclare(queueName, true, false, false, null); // 2nd parameter means durable
        channel.queueBind(queueName, exchangeName, routingKey);
        System.out.println("Joined queue " + queueName);
        return queueName;
    }

    private static void checkArguments(String[] args){
        if(args.length != 2 || !injuries.contains(args[0]) || !injuries.contains(args[1])){
            System.out.println("Usage:\n" +
                    "first_injury_type\n" +
                    "second_injury_type\n" +
                    "injury must be one of [ankle, knee, elbow]");
            System.exit(1);
        }
    }

}

