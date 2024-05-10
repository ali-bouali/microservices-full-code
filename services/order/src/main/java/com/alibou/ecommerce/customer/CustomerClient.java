package com.alibou.ecommerce.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
    name = "customer-service",
    url = "${application.config.customer-url}"
)
public interface CustomerClient {

  @GetMapping("/{customer-id}")
  Optional<CustomerResponse> findCustomerById(@PathVariable("customer-id") String customerId);
}
