package com.example.kafkaDemo.handler;

import com.example.kafkaDemo.event.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {

    @KafkaListener(topics = "order-created-topic")
    public void handle(OrderCreatedEvent orderCreatedEvent){
        System.out.println("Order received:" + orderCreatedEvent);
    }

}
