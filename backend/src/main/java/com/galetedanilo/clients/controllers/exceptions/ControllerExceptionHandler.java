package com.galetedanilo.clients.controllers.exceptions;

import com.galetedanilo.clients.services.exceptions.IntegrityViolationException;
import com.galetedanilo.clients.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException ex, HttpServletRequest req) {
        StandardError standardError = new StandardError();

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        standardError.setTimestamp(Instant.now());
        standardError.setStatus(httpStatus.value());
        standardError.setError("Resource not found");
        standardError.setMessage(ex.getMessage());
        standardError.setPath((req.getRequestURI()));

        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @ExceptionHandler(IntegrityViolationException.class)
    public ResponseEntity<StandardError> integrityViolationException(IntegrityViolationException ex, HttpServletRequest req) {
        StandardError standardError = new StandardError();

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        standardError.setTimestamp(Instant.now());
        standardError.setStatus(httpStatus.value());
        standardError.setError("Bad request");
        standardError.setMessage(ex.getMessage());
        standardError.setPath(req.getRequestURI());

        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationException(MethodArgumentNotValidException ex, HttpServletRequest req) {
        ValidationError validationError = new ValidationError();

        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        validationError.setTimestamp(Instant.now());
        validationError.setStatus(httpStatus.value());
        validationError.setError("Validation Exception");
        validationError.setMessage("Validation failed");
        validationError.setPath(req.getRequestURI());

        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            validationError.addFieldMessage(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(httpStatus).body(validationError);
    }
}
