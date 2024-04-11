package com.tedkvn.erp.rest.exception;

import org.springframework.http.HttpStatus;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class ResourceNotFoundException extends RestException {

    public ResourceNotFoundException(String msg) {
        super(isBlank(msg) ? "Resource Not Found" : msg, HttpStatus.BAD_REQUEST);
    }

}
