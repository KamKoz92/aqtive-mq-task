package com.github.kk.activemq.service;

import com.github.kk.activemq.model.MessageObject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.ObjectMessage;
import jakarta.jms.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTempQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class RequestorService {

    @Autowired
    JmsTemplate template;

    public Message sendTo(String destination, MessageObject messageObject) {
        log.info("Sending message with id {}", messageObject.getId());

//        template.convertAndSend(destination, messageObject);
        return template.sendAndReceive(destination, session -> {
            Message message = template.getMessageConverter().toMessage(messageObject, session);
            message.setJMSCorrelationID(UUID.randomUUID().toString());
            message.setJMSReplyTo(session.createTemporaryQueue());
            return message;
        });
    }


}
