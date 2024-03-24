package com.tedkvn.erp.rest.exception;

import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException {

    private final HttpStatus status;

    public RestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}