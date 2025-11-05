package com.e_commerce_system.backend.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(Long productId, int requested, int available) {
        super("Product ID " + productId + " has insufficient stock. Requested: " + requested + ", Available: " + available);
    }
}
