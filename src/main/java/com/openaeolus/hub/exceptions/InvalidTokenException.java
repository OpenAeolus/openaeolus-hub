package com.openaeolus.hub.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String token) {
        super("Invalid token");
    }
}
