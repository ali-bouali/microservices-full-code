package com.bouali.payment.validator;

import com.bouali.payment.exception.PaymentValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PaymentValidator<T> {

  private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = factory.getValidator();

  public void validate (T objectToValidate) {
    Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
    if (!violations.isEmpty()) {
      var errorMessages = violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.toSet());
      throw new PaymentValidationException(errorMessages);
    }
  }
}
