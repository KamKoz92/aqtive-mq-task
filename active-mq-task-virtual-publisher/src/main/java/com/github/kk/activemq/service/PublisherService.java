package com.github.kk.activemq.service;

import com.github.kk.activemq.model.MessageObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PublisherService {

    @Autowired
    JmsTemplate template;

    public void sendTo(String destination, MessageObject messageObject) {
        log.info("Sending message with id {}", messageObject.getId());
        template.convertAndSend(destination, messageObject);
    }
}
