package com.alibou.customer.exception;

import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerValidationException extends RuntimeException {

  private final Set<String> errorMessages;
}
