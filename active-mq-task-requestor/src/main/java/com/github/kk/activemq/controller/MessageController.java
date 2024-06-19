package com.github.kk.activemq.controller;

import com.github.kk.activemq.model.MessageObject;
import com.github.kk.activemq.service.RequestorService;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Value("${activemq.destination}")
    String destination;

    @Autowired
    RequestorService service;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody MessageObject messageObject) throws JMSException {
        Message msg = service.sendTo(destination, messageObject);
        return msg.getBody(MessageObject.class).getText();
//        return "Success:";
    }
}
