package com.example.kafkaDemo.service;

import com.example.kafkaDemo.dto.OrderDTO;
import com.example.kafkaDemo.event.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class OrderService {

    private final KafkaTemplate kafkaTemplate;

    public OrderService(@Qualifier("kafkaTemplate") KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String createOrder(OrderDTO orderDTO) throws ExecutionException, InterruptedException {
        String orderID = UUID.randomUUID().toString();
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(
                orderID,
                orderDTO.getDoctorSurname(),
                orderDTO.getPayWay()
        );
        SendResult<String,OrderCreatedEvent> result =
                (SendResult<String, OrderCreatedEvent>) kafkaTemplate.send("order-created-topic",orderID,orderCreatedEvent).get();

        System.out.println("Topic: "+ result.getRecordMetadata().topic());
        System.out.println("Partition: "+ result.getRecordMetadata().partition());
        System.out.println("Offset: "+ result.getRecordMetadata().offset());
        System.out.println("Timestamp: "+ result.getRecordMetadata().timestamp());

        System.out.println("OrderID: "+orderID);
        return orderID;
    }
}
