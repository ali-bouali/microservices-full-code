package com.bouali.payment.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

  private final AmqpTemplate amqpTemplate;

  public void sendNotification(String message) {
    log.info("Sending notification with body = < {} >", message);
    amqpTemplate.convertAndSend("payment-exchange", "payment", message);
  }
}
