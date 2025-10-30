package com.backend.jjj.cinema_api.error;

public class UnableSeatException extends RuntimeException {
    public UnableSeatException(String message) {
        super(message);
    }
}
