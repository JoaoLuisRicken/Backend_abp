package com.backend.jjj.cinema_api.error;

public class InactiveMovieException extends RuntimeException {
    public InactiveMovieException(String message) {
        super(message);
    }
}
