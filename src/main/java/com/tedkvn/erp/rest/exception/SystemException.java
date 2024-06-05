package com.tedkvn.erp.rest.exception;

import org.springframework.http.HttpStatus;

public class SystemException extends RestException {


    public SystemException() {
        super("There was an unexpected issue. Please try again later. If you continue " +
                        "to experience this issue, please contact the administrator.",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public SystemException(String msg) {
        super(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
