package com.tedkvn.erp.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    private HttpStatus status;

    private String message;

    private String resourceName;

    private String path;

    public ErrorResponse(HttpStatus status, String resourceName, String message, String path) {
        super();
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.resourceName = resourceName;
        this.message = message;
        this.path = path;
    }

    public ErrorResponse(String resourceName, String message, String path) {
        super();
        this.timestamp = LocalDateTime.now();
        // default 500 error
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.resourceName = resourceName;
        this.message = message;
        this.path = path;
    }
}
