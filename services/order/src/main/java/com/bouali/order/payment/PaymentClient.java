package com.bouali.order.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "shipping-service",
    url = "${application.config.payment-url}"
)
public interface PaymentClient {

  @PostMapping
  Integer requestOrderPayment(@RequestBody PaymentRequest request);
}
