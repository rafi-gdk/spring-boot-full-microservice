package com.sonu.customer.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse error = new ErrorResponse("Invalid Input", errors);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleCustomerDataException(Exception e) {
        List<String> details = new ArrayList<>();
        details.add(e.getLocalizedMessage());
        if (e instanceof ConstraintViolationException) {
            ErrorResponse error = new ErrorResponse("Invalid Input", details);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        if (e instanceof InsufficientAuthenticationException) {
            ErrorResponse error = new ErrorResponse("Invalid Authentication", details);
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
        ErrorResponse error = new ErrorResponse("Internal Server Error", details);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
