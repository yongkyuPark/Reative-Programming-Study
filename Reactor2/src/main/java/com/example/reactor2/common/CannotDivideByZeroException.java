package com.example.reactor2.common;

public class CannotDivideByZeroException extends RuntimeException {
    public CannotDivideByZeroException(String message) {
        super(message);
    }
}
