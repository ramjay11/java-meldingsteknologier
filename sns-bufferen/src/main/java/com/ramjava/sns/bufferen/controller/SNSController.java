package com.ramjava.sns.bufferen.controller;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SNSController {
    @Autowired
    private AmazonSNSClient amazonSNSClient;

    //
    private String TOPIC_ARN = "arn:aws:sns:us-east-1:090894398:mijn-sns-onderwerp";

    // Subscribe using email
    @GetMapping("/subscribe/{email}")
    public String subscribeToSNSTopic(@PathVariable("email") String email) {
        var subscribeRequest = new SubscribeRequest(TOPIC_ARN, "email", email);
        amazonSNSClient.subscribe(subscribeRequest);
        return "Controleer uw e-mail";
    }

    // Publish to Topic
    @GetMapping("/publish/{msg}")
    public String publishToTopic(@PathVariable("msg") String msg) {
        var publishRequest = new PublishRequest(TOPIC_ARN, msg, "Meld u aan voor de meldingsservice");
        amazonSNSClient.publish(publishRequest);
        return "Bericht gepubliceerd";
    }
}
