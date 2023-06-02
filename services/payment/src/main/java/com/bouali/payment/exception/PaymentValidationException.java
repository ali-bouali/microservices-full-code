package com.bouali.payment.exception;

import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PaymentValidationException extends RuntimeException {

  private final Set<String> errorMessages;
}
