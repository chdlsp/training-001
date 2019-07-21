package com.rasol.training001.exception.handler;

import com.rasol.training001.exception.RestException;
import com.rasol.training001.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<Object> handleRestException(RestException e,
                                                      HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(e.getHttpStatus(), request, e.getMessage());
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e,
//                                                      HttpServletRequest request) {
//        ErrorResponse errorResponse = new ErrorResponse(request, e.getMessage());
//        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
//    }
}
