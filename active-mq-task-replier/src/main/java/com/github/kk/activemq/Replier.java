package com.github.kk.activemq;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.Scanner;

public class Replier {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination requestChannel = session.createQueue("requestChannel");
        Destination replyChannel = session.createQueue("replyChannel");

        MessageProducer producer = session.createProducer(replyChannel);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        MessageConsumer consumer = session.createConsumer(requestChannel);
        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage textMessage) {
                try {
                    System.out.println("Received: " + textMessage.getText());
                    producer.send(message);
                } catch (JMSException e) {
                    System.err.println(e.getMessage());
                }
            }
        });

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter message body (type 'exit' to quit):");
            String input = scanner.nextLine();
            if ("exit".equals(input)) {
                break;
            }
        }

        scanner.close();
        session.close();
        connection.close();
    }
}