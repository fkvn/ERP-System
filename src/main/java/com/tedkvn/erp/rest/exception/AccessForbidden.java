package com.tedkvn.erp.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.apache.commons.lang3.StringUtils.isBlank;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessForbidden extends RestException {

    public AccessForbidden(String message) {
        super(isBlank(message) ? "Forbidden Access" : message, HttpStatus.FORBIDDEN);
    }
}
