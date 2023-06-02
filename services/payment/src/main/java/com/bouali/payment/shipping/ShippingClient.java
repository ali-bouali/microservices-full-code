package com.bouali.payment.shipping;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "shipping-service",
    url = "${application.config.shipping-url}"
)
public interface ShippingClient {

  @PostMapping
  Integer initiateShipping(@RequestBody ShippingRequest request);
}
