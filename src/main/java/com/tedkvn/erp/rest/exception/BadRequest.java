package com.tedkvn.erp.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.apache.commons.lang3.StringUtils.isBlank;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequest extends RestException {

    public BadRequest(String message) {
        super(isBlank(message) ? "Bad Request" : message, HttpStatus.BAD_REQUEST);
    }
}
