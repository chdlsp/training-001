package com.rasol.training001.exception.handler;

import com.rasol.training001.exception.RestException;
import com.rasol.training001.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<Object> handleRestException(RestException e,
                                                      HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(e.getHttpStatus(), request, e.getMessage());
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e,
                                                      HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, request, e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                               HttpServletRequest request) {

        String error = e.getBindingResult().getAllErrors().stream().map((objectError) ->
                    objectError.getObjectName() + "." + ((FieldError) objectError).getField() + ": " + objectError.getDefaultMessage()).collect(Collectors.joining(", "));

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, request, error);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
