package com.ramjava.guias.conejomq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConejoMQConfig {
    @Value("${conejomq.queue.name}")
    private String queue;

    @Value("${conejomq.exchange.name}")
    private String exchange;

    @Value("${conejomq.routing.key}")
    private String routingKey;


    // spring bean for queue
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    //
    @Bean
    public TopicExchange exchange() {
        return new  TopicExchange(exchange);
    }

    // Bind between queue and exchange using routing key
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    // SpringBoot auto-configuration will automatically configure these beans
    // ConnectionFactory
    // RabbitTemplate
    // RabbitAdmin
}
