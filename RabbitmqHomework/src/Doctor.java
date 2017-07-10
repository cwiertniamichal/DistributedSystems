import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

public class Doctor {
    private static final String hospitalExchangeName = "Hospital";
    private static final String adminExchangeName = "Admin";
    private static final String ankleInjury = "ankle";
    private static final String kneeInjury = "knee";
    private static final String elbowInjury = "elbow";

    public static void main(String[] args) throws IOException, TimeoutException{

        // doctor's name will be used as binding key for reply queue also
        String doctorName = args[0];
        System.out.println("Doctor: \"" + doctorName + "\" has started");

        // reader
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // connection and channel
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel hospitalChannel = connection.createChannel();
        Channel adminChannel = connection.createChannel();

        // exchange
        hospitalChannel.exchangeDeclare(hospitalExchangeName, BuiltinExchangeType.TOPIC);
        adminChannel.exchangeDeclare(adminExchangeName, BuiltinExchangeType.FANOUT);

        // create queue for test results
        String hospitalQueueName = declareAndBindQueue(hospitalChannel, "", hospitalExchangeName, doctorName, true);

        // create queue for admin info
        String adminQueueName = declareAndBindQueue(adminChannel, "", adminExchangeName, "", true);

        // create queues for injuries (if not already created)
        declareAndBindQueue(hospitalChannel, ankleInjury,  hospitalExchangeName, ankleInjury, false);
        declareAndBindQueue(hospitalChannel, kneeInjury, hospitalExchangeName, kneeInjury, false);
        declareAndBindQueue(hospitalChannel, elbowInjury,  hospitalExchangeName, elbowInjury, false);

        // create consumer for hospital channel
        Consumer hospitalConsumer = getDefaultConsumer(hospitalChannel);

        // create consumer for admin channel
        Consumer adminConsumer = getDefaultConsumer(adminChannel);

        hospitalChannel.basicConsume(hospitalQueueName, true, hospitalConsumer);
        adminChannel.basicConsume(adminQueueName, true, adminConsumer);

        while(true){
            // read message nad injury type
            System.out.println("Enter injury type: ");
            String injury = in.readLine(); // will be used as routing key
            if(injury.equals("exit"))
                break;
            if(!injury.toLowerCase().equals("ankle") && !injury.toLowerCase().equals("knee") && !injury.toLowerCase().equals("elbow")) {
                System.out.println("Wrong injury type!");
                continue;
            }
            System.out.println("Enter patient name: ");
            String name = in.readLine();

            hospitalChannel.basicPublish(hospitalExchangeName, injury, new AMQP.BasicProperties.Builder().replyTo(doctorName).build(), (injury + " " + name).getBytes("UTF-8")); // should be persistent??
            System.out.println("Sent: " + injury + " " + name);
        }
        adminChannel.close();
        hospitalChannel.close();
        connection.close();
    }

    private static DefaultConsumer getDefaultConsumer(Channel channel){
        return new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException{
                String message = new String(body, "UTF-8");
                System.out.println("Received: " + message);
            }
        };
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

}
