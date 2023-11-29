package com.bouali.ecommerce.notification;

import com.bouali.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record NotificationRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod
) {
}
