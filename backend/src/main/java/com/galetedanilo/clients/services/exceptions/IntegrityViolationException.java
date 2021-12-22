package com.galetedanilo.clients.services.exceptions;

public class IntegrityViolationException extends RuntimeException {

    public IntegrityViolationException(String message) {
        super(message);
    }
}
