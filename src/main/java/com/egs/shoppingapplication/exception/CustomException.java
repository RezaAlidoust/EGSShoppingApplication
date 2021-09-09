package com.egs.shoppingapplication.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;
    private final String description;

    public CustomException(String message, HttpStatus httpStatus, String description) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.description = description;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getDescription() {
        return description;
    }
}

