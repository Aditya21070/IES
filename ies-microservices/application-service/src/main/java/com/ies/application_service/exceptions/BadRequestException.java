package com.ies.application_service.exceptions;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 2863343499006931940L;

	public BadRequestException(String message) {
        super(message);
    }

}
