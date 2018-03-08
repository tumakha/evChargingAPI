package com.tantalumcorporation.evChargingAPI.rest;

import static java.util.Collections.singletonMap;
import static java.util.stream.Collectors.toList;

import java.util.Map;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Yuriy Tumakha
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

  @ExceptionHandler
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map handle(MethodArgumentNotValidException exception) {
    return error(exception.getBindingResult().getFieldErrors().stream()
        .map(FieldError::getDefaultMessage)
        .collect(toList()));
  }

  @ExceptionHandler
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map handle(ConstraintViolationException exception) {
    return error(exception.getConstraintViolations().stream()
        .map(violation -> violation.getPropertyPath().toString() + " " + violation.getMessage())
        .collect(toList()));
  }

  private Map error(Object message) {
    return singletonMap("error", message);
  }

}
