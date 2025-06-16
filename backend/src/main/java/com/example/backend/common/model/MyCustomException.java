package com.example.backend.common.model;

public class MyCustomException extends RuntimeException {

    public MyCustomException(String message) {
        super(message);
    }
}
