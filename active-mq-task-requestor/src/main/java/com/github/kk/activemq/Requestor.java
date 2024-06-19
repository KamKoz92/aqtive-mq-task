package com.github.kk.activemq;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Requestor {
    public static void main(String[] args) throws JMSException {
        SpringApplication.run(Requestor.class);
    }
}