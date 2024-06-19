package com.github.kk.activemq.controller;

import com.github.kk.activemq.model.MessageObject;
import com.github.kk.activemq.service.PublisherService;
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
    PublisherService service;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody MessageObject messageObject) {
        service.sendTo(destination, messageObject);
        return "Message send";
    }
}
