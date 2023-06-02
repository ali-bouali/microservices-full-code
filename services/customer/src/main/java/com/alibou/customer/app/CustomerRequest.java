package com.alibou.customer.app;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CustomerRequest(
    String id,
    @NotNull(message = "Customer name is required")
    String name,
    @NotNull(message = "Customer Email is required")
    @Email(message = "Customer Email is not a valid email address")
    String email,
    @Positive(message = "Customer age should be positive")
    int age
) {

}
