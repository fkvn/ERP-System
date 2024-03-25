package com.tedkvn.erp.rest.controllerAdvice;

import com.tedkvn.erp.rest.exception.RestException;
import com.tedkvn.erp.rest.response.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionResolver extends ExceptionHandlerExceptionResolver {

    private static final String MESSAGE_KEY = "message";
    private static final String STATUS_KEY = "status";
    private static final String RESOURCE_NAME_KEY = "resourceName";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = buildErrorResponse(ex, request);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    public ErrorResponse buildErrorResponse(@NotNull Exception ex, WebRequest request) {

        String resourceName = ex.getClass().getSimpleName();
        String message = null;
        try {
            message = ex.getCause().getLocalizedMessage();
        } catch (Exception e) {
            message = ex.getLocalizedMessage();
        }

        String path = request.getDescription(true).split(";")[0].split("=")[1];

        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, resourceName, message, path);
    }

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorResponse> handleRestException(RestException ex, WebRequest request) {
        HashMap<String, Object> customFields = new HashMap<>();
        customFields.put(STATUS_KEY, ex.getStatus());

        ErrorResponse errorResponse = buildCustomErrorResponse(ex, request, customFields);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    public ErrorResponse buildCustomErrorResponse(Exception ex, WebRequest request,
                                                  @NotNull HashMap<String, Object> customFields) {

        ErrorResponse errorResponse = buildErrorResponse(ex, request);
        String resourceName = (String) customFields.get(RESOURCE_NAME_KEY);
        String message = (String) customFields.get(MESSAGE_KEY);
        HttpStatus status = (HttpStatus) customFields.get(STATUS_KEY);

        Optional.ofNullable(resourceName).ifPresent(errorResponse::setResourceName);
        Optional.ofNullable(message).ifPresent(errorResponse::setMessage);
        Optional.ofNullable(status).ifPresent(errorResponse::setStatus);

        return errorResponse;
    }

    @ExceptionHandler({EmptyResultDataAccessException.class, EntityNotFoundException.class,
            NullPointerException.class, NoSuchElementException.class,
            UsernameNotFoundException.class})
    protected ResponseEntity<ErrorResponse> handleEmptyResultDataAccessException(Exception ex,
                                                                                 WebRequest request) {
        HashMap<String, Object> customFields = new HashMap<>();
        customFields.put(MESSAGE_KEY, "Resource Not Found");
        customFields.put(STATUS_KEY, HttpStatus.NOT_FOUND);

        ErrorResponse errorResponse = buildCustomErrorResponse(ex, request, customFields);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler({ClassCastException.class})
    protected ResponseEntity<Object> handleBadRequestException(Exception ex, WebRequest request) {

        HashMap<String, Object> customFields = new HashMap<>();
        customFields.put(STATUS_KEY, HttpStatus.BAD_REQUEST);
        ErrorResponse errorResponse = buildCustomErrorResponse(ex, request, customFields);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler({BadCredentialsException.class})
    protected ResponseEntity<Object> handleBadCredentialsException(Exception ex,
                                                                   WebRequest request) {

        HashMap<String, Object> customFields = new HashMap<>();
        customFields.put(MESSAGE_KEY,
                "Failed to authenticate since no credentials were provided or " +
                        "credentials and password do not match");
        ErrorResponse errorResponse = buildCustomErrorResponse(ex, request, customFields);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, WebRequest request) {

        HashMap<String, Object> customFields = new HashMap<>();
        customFields.put(STATUS_KEY, HttpStatus.BAD_REQUEST);

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errors = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        String message = String.join("; ", errors);  // Join with newlines for readability
        customFields.put(MESSAGE_KEY, message);

        ErrorResponse errorResponse = buildCustomErrorResponse(ex, request, customFields);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }


}
