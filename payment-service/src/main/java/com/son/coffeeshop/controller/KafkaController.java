package com.son.coffeeshop.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    @KafkaListener(topics = "sampletopic", groupId = "coffee-shop")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}
