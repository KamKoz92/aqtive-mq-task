package com.github.kk.activemq.configuration;

import com.github.kk.activemq.model.MessageObject;
import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.UUID;

@Configuration
public class ActiveMQConfig {

    @Value("${spring.activemq.broker-url}")
    String brokerUrl;

    @Value("${spring.activemq.user}")
    String userName;

    @Value("${spring.activemq.password}")
    String password;

    @Value("${activemq.clientId}")
    String clientId;

    @Bean(name = "jmsFactory")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                          DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setMessageConverter(jacksonMessageConverter());
        factory.setConnectionFactory(singleConnectionFactory());
        return factory;
    }

    ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        activeMQConnectionFactory.setUserName(userName);
        activeMQConnectionFactory.setPassword(password);
        return activeMQConnectionFactory;
    }

    SingleConnectionFactory singleConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        activeMQConnectionFactory.setUserName(userName);
        activeMQConnectionFactory.setPassword(password);

        SingleConnectionFactory singleConnectionFactory = new SingleConnectionFactory(activeMQConnectionFactory);
        singleConnectionFactory.setReconnectOnException(true);
        singleConnectionFactory.setClientId(clientId);
        return singleConnectionFactory;
    }

    MessageConverter jacksonMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}