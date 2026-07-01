package com.ies.auth_service.exception;

public class InvalidCredentialsException extends RuntimeException {

    private static final long serialVersionUID = 3675223973420034282L;

	public InvalidCredentialsException(String message) {
        super(message);
    }
}
