package com.bouali.payment.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

  private static final String EXCHANGE_NAME = "payment-exchange";
  private static final String QUEUE_NAME = "payment-queue";

  @Bean
  public Queue createQueue() {
    //For learning purpose - durable=false,
    // in a real project you better set this as true.
    return new Queue(QUEUE_NAME, true);
  }
  @Bean
  public Exchange directExchange() {
    // durable=true, autoDelete=false
    return new DirectExchange(EXCHANGE_NAME, true, false);
  }
  @Bean
  public Binding queueBinding() {
    return new Binding(QUEUE_NAME, Binding.DestinationType.QUEUE, EXCHANGE_NAME, "payment", null);
  }
}
