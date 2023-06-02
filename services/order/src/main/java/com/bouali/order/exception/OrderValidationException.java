package com.bouali.order.exception;

import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderValidationException extends RuntimeException {

  private final Set<String> errorMessages;
}
