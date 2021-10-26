package com.psoft.crdb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class CRDBExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public Object handleNotFound(EntityNotFoundException entityNotFoundException){
        return buildMessage(HttpStatus.NOT_FOUND,
                entityNotFoundException.getMessage());
    }

    @ExceptionHandler(EntityExistsException.class)
    public Object handleAlreadyExists(EntityExistsException entityExistsException){
        return buildMessage(HttpStatus.BAD_REQUEST,
                entityExistsException.getMessage());
    }

    private CustomErrorMessage buildMessage(HttpStatus status, String message){
        CustomErrorMessage customErrorMessage = new CustomErrorMessage();
        customErrorMessage.setMessage(message);
        customErrorMessage.setCode(status.value());
        customErrorMessage.setStatus(status.getReasonPhrase());
        customErrorMessage.setTimestamp(LocalDateTime.now());
        return customErrorMessage;
    }
}
