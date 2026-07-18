package com.ies.plan_service.exception;

public class DuplicateResourceException extends RuntimeException {

    private static final long serialVersionUID = 8580528734136083840L;

	public DuplicateResourceException(String message) {
        super(message);
    }

}
