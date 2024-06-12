package com.epam.activemq;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

public class Subscriber {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        Connection connection = connectionFactory.createConnection();
        if (Arrays.asList(args).contains("durable")) {
            connection.setClientID("durable");
        } else {
            connection.setClientID(UUID.randomUUID().toString());
        }
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("myTopic");

        MessageConsumer consumer;
        if (Arrays.asList(args).contains("durable")) {
            consumer = session.createDurableSubscriber(topic, "mySubscriber");
        } else {
            consumer = session.createConsumer(topic);
        }

        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage textMessage) {
                try {
                    System.out.println("Received: " + textMessage.getJMSMessageID() + ": " + textMessage.getText());
                } catch (JMSException e) {
                    System.err.println(e.getMessage());
                }
            }
        });

        Scanner scanner = new Scanner(System.in);
        while (true) {
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