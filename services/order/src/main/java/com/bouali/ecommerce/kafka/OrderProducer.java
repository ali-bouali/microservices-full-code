package com.bouali.ecommerce.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    public void sendOrderConfirmation(OrderConfirmation student) {

        Message<OrderConfirmation> message = MessageBuilder
                .withPayload(student)
                .setHeader(TOPIC, "order-topic")
                .build();

        kafkaTemplate.send(message);
    }
}
