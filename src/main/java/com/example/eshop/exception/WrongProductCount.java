package com.example.eshop.exception;

public class WrongProductCount extends RuntimeException {
    public WrongProductCount(String message) {
        super(message);
    }
    public WrongProductCount(){

    }
}
