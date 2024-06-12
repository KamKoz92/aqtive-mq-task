package com.github.kk.activemq;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

public class VirtualSubscriber {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        Connection connection = connectionFactory.createConnection();
        connection.setClientID(UUID.randomUUID().toString());

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        String queueName = "Consumer." + args[0] + ".VirtualTopic.myVirtualTopic";
        Queue queue = session.createQueue(queueName);

        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage textMessage) {
                try {
                    System.out.println("Received: " + textMessage.getText());
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