package com.bouali.payment.handler;

import com.bouali.payment.exception.BusinessException;
import com.bouali.payment.exception.PaymentValidationException;
import jakarta.persistence.EntityNotFoundException;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handle(EntityNotFoundException exp) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(exp.getMessage());
  }

  @ExceptionHandler(PaymentValidationException.class)
  public ResponseEntity<Set<String>> handle(PaymentValidationException exp) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exp.getErrorMessages());
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<String> handle(BusinessException exp) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exp.getMsg());
  }
}
