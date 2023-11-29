package com.bouali.ecommerce.payment;

import com.bouali.ecommerce.order.PaymentMethod;
import java.math.BigDecimal;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId
) {
}
