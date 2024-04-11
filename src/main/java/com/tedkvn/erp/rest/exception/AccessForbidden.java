package com.tedkvn.erp.rest.exception;

import org.springframework.http.HttpStatus;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class AccessForbidden extends RestException {

    public AccessForbidden(String message) {
        super(isBlank(message) ? "Forbidden Access" : message, HttpStatus.FORBIDDEN);
    }
}
