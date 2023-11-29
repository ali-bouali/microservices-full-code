package com.bouali.ecommerce.orderline;

import com.bouali.ecommerce.order.Order;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        Integer productId,
        double quantity
) {
}
