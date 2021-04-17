package com.rezdy.lunch.exception;

import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rezdy.lunch.model.ErrorResponse;

/**
 * Exception handler for lunch APIs exceptions.
 * 
 * @author bohuazhang
 */
@RestControllerAdvice
public class LunchExceptionHandler
{

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(DateTimeParseException exception)
    {
        return new ResponseEntity<>(new ErrorResponse().setErrorMessage(exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
