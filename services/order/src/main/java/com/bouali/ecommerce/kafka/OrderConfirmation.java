package com.bouali.ecommerce.kafka;

import com.bouali.ecommerce.customer.CustomerResponse;
import com.bouali.ecommerce.order.PaymentMethod;
import com.bouali.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
