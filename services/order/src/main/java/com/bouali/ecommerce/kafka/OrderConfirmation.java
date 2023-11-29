package com.bouali.ecommerce.kafka;

import com.bouali.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        int totalProducts

) {
}
