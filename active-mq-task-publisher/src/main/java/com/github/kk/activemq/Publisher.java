package com.github.kk.activemq;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.Scanner;
import java.util.UUID;

public class Publisher {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        Connection connection = connectionFactory.createConnection();
        connection.setClientID(UUID.randomUUID().toString());
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createTopic("VirtualTopic.myTopic");
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter message body (type 'exit' to quit):");
            String input = scanner.nextLine();
            TextMessage message = session.createTextMessage(input);
            producer.send(message);
            if ("exit".equals(input)) {
                break;
            }
        }

        scanner.close();
        session.close();
        connection.close();
    }
}