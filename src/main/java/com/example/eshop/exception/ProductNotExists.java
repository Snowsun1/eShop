package com.example.eshop.exception;

public class ProductNotExists extends RuntimeException {

    public ProductNotExists() {
    }

    public ProductNotExists(String message) {
        super(message);
    }
}
