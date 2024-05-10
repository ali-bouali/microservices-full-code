package com.alibou.ecommerce.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

  public Payment toPayment(PaymentRequest request) {
    if (request == null) {
      return null;
    }
    return Payment.builder()
        .id(request.id())
        .paymentMethod(request.paymentMethod())
        .amount(request.amount())
        .orderId(request.orderId())
        .build();
  }
}
