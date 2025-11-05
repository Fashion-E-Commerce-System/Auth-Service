package com.e_commerce_system.backend.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<?> handleInsufficientStock(InsufficientStockException ex) {
        return ResponseEntity.badRequest().body(Map.of(
                "error", "Insufficient Stock",
                "message", ex.getMessage()
        ));
    }

}