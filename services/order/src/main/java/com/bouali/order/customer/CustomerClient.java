package com.bouali.order.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "customer-service",
    url = "${application.config.customer-url}"
)
public interface CustomerClient {

  @GetMapping("/exists/{customer-id}")
  boolean customerExists(@PathVariable("customer-id") String customerId);
  default boolean customerNotExists(String customerId) {
    return !this.customerExists(customerId);
  }
}
