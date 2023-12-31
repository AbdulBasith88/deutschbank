package com.deutschebank.ms.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidOperationException.class)
    protected ResponseEntity<Object> handleInvalidOperationException(InvalidOperationException ex, WebRequest req) {
        Object resBody = "Invalid signal operation/s";
        return handleExceptionInternal(ex, resBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, req);
    }

}
