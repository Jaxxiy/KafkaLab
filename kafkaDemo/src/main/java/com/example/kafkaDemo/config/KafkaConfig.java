package com.example.kafkaDemo.config;

import com.example.kafkaDemo.event.OrderCreatedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Autowired
    Environment environment;

    Map<String,Object> producerKonfig(){
        Map<String,Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                environment.getProperty("spring.kafka.producer.bootstrap-servers"));
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                environment.getProperty("spring.kafka.producer.key-serializer"));
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                environment.getProperty("spring.kafka.producer.value-serializer"));
        config.put(ProducerConfig.ACKS_CONFIG,
                environment.getProperty("spring.kafka.producer.acks"));
        return config;
    }

    @Bean
    ProducerFactory<String, OrderCreatedEvent> producerFactory(){
        return new DefaultKafkaProducerFactory<String,OrderCreatedEvent>(producerKonfig());
    }

    @Bean
    KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate(){
        return new KafkaTemplate<String, OrderCreatedEvent>(producerFactory());
    }

    @Bean
    NewTopic createTopic(){
        return TopicBuilder.name("order-created-topic")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas","2"))
                .build();
    }
}
