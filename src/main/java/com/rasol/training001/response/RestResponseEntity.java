package com.rasol.training001.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public class RestResponseEntity extends ResponseEntity {
    public RestResponseEntity(HttpServletRequest request, Object body){
        super(new SuccessResponse(request, body), HttpStatus.OK);
    }
}
