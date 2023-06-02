package com.alibou.customer.handler;

import com.alibou.customer.exception.CustomerValidationException;
import com.alibou.customer.exception.CustomerNotFoundException;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomerNotFoundException.class)
  public ResponseEntity<String> handle(CustomerNotFoundException exp) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(exp.getMsg());
  }

  @ExceptionHandler(CustomerValidationException.class)
  public ResponseEntity<Set<String>> handle(CustomerValidationException exp) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exp.getErrorMessages());
  }
}
