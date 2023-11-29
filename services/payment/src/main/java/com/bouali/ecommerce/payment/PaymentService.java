package com.bouali.ecommerce.payment;

import com.bouali.ecommerce.notification.NotificationProducer;
import com.bouali.ecommerce.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final PaymentRepository repository;
  private final PaymentMapper mapper;
  private final NotificationProducer notificationProducer;

  public Integer createPayment(PaymentRequest request) {
    var payment = this.repository.save(this.mapper.toPayment(request));
    this.notificationProducer.sendNotification(
            new NotificationRequest(
                    request.amount(),
                    request.paymentMethod()
            )
    );
    return payment.getId();
  }
}
