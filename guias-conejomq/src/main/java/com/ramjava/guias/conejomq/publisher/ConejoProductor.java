package com.ramjava.guias.conejomq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConejoProductor {
    @Value("${conejomq.exchange.name}")
    private String exchange;

    @Value("${conejomq.routing.key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(ConejoProductor.class);

    // Constructor-based DI
    private RabbitTemplate rabbitTemplate;

    // Parameterized constructor with single parameter can omit @Autowired
    public ConejoProductor(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        LOGGER.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
