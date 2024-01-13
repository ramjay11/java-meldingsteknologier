package com.ramjava.sqs.bufferen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SQSController {
    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.endpoint.uri}")
    private String endpoint;

    // Send message to Queue
    @GetMapping("/put/{msg}")
    public void putMessageToQueue(@PathVariable("msg") String message) {
        queueMessagingTemplate.send(endpoint, MessageBuilder.withPayload(message).build());
    }

    // Read messages from SQS
    @SqsListener("sqs-wachtrij")
    public void loadMessageFromQueue(String message) {
        System.out.println("Queue message: " + message);
    }
}
