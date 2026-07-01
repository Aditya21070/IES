package com.ies.citizen_service.exception;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 2863343499006931940L;

	public BadRequestException(String message) {
        super(message);
    }

}
