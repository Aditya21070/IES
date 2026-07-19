package com.ies.payment_service.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
public class ErrorResponse {

    private LocalDateTime timestamp;

    private int status;

    private String message;

    private Map<String, String> errors;

}
