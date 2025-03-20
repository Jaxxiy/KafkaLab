package com.example.kafkaDemo.controller;

import com.example.kafkaDemo.dto.OrderDTO;
import com.example.kafkaDemo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class KafkaController {

    private OrderService orderService;

    @Autowired
    public KafkaController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) throws ExecutionException, InterruptedException {
        String orderID = orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderID);
    }
}
