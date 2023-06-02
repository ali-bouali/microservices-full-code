package com.bouali.payment.payment;

import com.bouali.payment.notification.NotificationService;
import com.bouali.payment.validator.PaymentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final PaymentRepository repository;
  private final PaymentValidator<PaymentRequest> validator;
  private final PaymentMapper mapper;
  private final NotificationService notificationService;

  public Integer createPayment(PaymentRequest request) {
    this.validator.validate(request);
    var payment = this.repository.save(this.mapper.toPayment(request));
    this.notificationService.sendNotification(String.format("Payment accepted for order number: %d", request.orderId()));
    return payment.getId();
  }
}
