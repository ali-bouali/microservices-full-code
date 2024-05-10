package com.alibou.ecommerce.order;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService service;

  @PostMapping
  public ResponseEntity<Integer> createOrder(
      @RequestBody @Valid OrderRequest request
  ) {
    return ResponseEntity.ok(this.service.createOrder(request));
  }

  @GetMapping
  public ResponseEntity<List<OrderResponse>> findAll() {
    return ResponseEntity.ok(this.service.findAllOrders());
  }

  @GetMapping("/{order-id}")
  public ResponseEntity<OrderResponse> findById(
      @PathVariable("order-id") Integer orderId
  ) {
    return ResponseEntity.ok(this.service.findById(orderId));
  }
}
