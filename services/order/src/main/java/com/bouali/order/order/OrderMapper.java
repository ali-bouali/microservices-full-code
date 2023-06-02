package com.bouali.order.order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {


  public Order toOrder(OrderRequest request) {
    if (request == null) {
      return null;
    }
    return Order.builder()
        .id(request.id())
        .reference(request.reference())
        .paymentMethod(request.paymentMethod())
        .quantity(request.quantity())
        .amount(request.amount())
        .item(request.item())
        .customerId(request.customerId())
        .build();
  }

  public OrderResponse fromOrder(Order order) {
    return new OrderResponse(
        order.getId(),
        order.getReference(),
        order.getAmount(),
        order.getItem(),
        order.getQuantity(),
        order.getPaymentMethod(),
        order.getCustomerId()
    );
  }
}
