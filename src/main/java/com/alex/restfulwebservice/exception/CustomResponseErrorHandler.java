package com.alex.restfulwebservice.exception;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseErrorHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
    ExceptionDetails exceptionDetails = new ExceptionDetails(
        LocalDateTime.now(),
        ex.getMessage(),
        request.getDescription(false)
    );

    return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<Object> userNotFoundException(Exception ex, WebRequest request) {
    ExceptionDetails exceptionDetails = new ExceptionDetails(
        LocalDateTime.now(),
        "user is is not found",
        request.getDescription(false)
    );

    return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override()
  public final ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {

    ExceptionDetails exceptionDetails = new ExceptionDetails(
        LocalDateTime.now(),
        ex.getFieldError().getDefaultMessage(),
        request.getDescription(false)
    );

    return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
  }

}
