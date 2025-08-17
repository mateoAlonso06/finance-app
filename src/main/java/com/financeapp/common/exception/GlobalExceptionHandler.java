package com.financeapp.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ExpenseNotFoundException exc) {
        ErrorResponse errorDetails = new ErrorResponse(
                Instant.now(),
                "There is no expense with that ID"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        //log si es necesario con exc.getMessage()
    }
}
