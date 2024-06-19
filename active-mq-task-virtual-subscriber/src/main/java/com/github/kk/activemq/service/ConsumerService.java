package com.github.kk.activemq.service;

import com.github.kk.activemq.model.MessageObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class ConsumerService {

    @JmsListener(destination = "${activemq.source}", containerFactory = "jmsFactory")
    public void consume(MessageObject messageObject) {
        log.info("Consumer. Message with id {}. Message text: {}", messageObject.getId(), messageObject.getText());
    }

    @JmsListener(destination = "${activemq.source}", containerFactory = "jmsFactory")
    public void consume2(MessageObject messageObject) {
        log.info("Consumer 2. Message with id {}. Message text: {}", messageObject.getId(), messageObject.getText());
    }
}
