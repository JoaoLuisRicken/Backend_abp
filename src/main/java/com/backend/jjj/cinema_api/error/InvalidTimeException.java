package com.backend.jjj.cinema_api.error;

public class InvalidTimeException extends RuntimeException {
    public InvalidTimeException(String message) {
        super(message);
    }
}
