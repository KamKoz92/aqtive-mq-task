package com.github.kk.activemq.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageObject implements Serializable {
    private String id;
    private String text;
}
