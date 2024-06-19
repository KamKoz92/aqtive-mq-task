package com.github.kk.activemq.service;

import com.github.kk.activemq.model.MessageObject;
import jakarta.jms.Destination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
class ReplierService {

    @Autowired
    JmsTemplate template;

    @JmsListener(destination = "${activemq.source}", containerFactory = "jmsFactory")
    public void consume(MessageObject messageObject,
                        @Header("jms_replyTo") Destination replyDestination,
                        @Header("jms_correlationId") String correlationId) {

        log.info("Got message with corrId {}. Message text: {}", correlationId, messageObject.getText());
        MessageObject newMessageObject = new MessageObject();
        String newRandomText = UUID.randomUUID().toString();
        log.info("New message text {}", newRandomText);
        newMessageObject.setText(newRandomText);
        template.convertAndSend(replyDestination, newMessageObject);
    }
}
