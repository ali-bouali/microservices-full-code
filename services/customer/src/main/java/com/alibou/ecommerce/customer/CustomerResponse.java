package com.alibou.ecommerce.customer;

import jakarta.validation.constraints.NotNull;

public record CustomerResponse(
    String id,
    String name,
    String email,
    Address address
) {

}
