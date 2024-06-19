package com.github.kk.activemq;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

@SpringBootApplication
public class VirtualSubscriber {
    public static void main(String[] args) {
        SpringApplication.run(VirtualSubscriber.class);
    }
}