package com.ies.plan_service.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -298752548044371468L;

	public ResourceNotFoundException(String message) {
        super(message);
    }

}
