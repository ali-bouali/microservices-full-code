package com.alibou.customer.app;

public record CustomerResponse(
    String id,
    String name,
    String email,
    int age
) {

}
