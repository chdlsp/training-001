package com.rasol.training001.exception;

import com.rasol.training001.code.ErrorCodes;
import org.springframework.http.HttpStatus;

public class NotFoundException extends RestException {
    private NotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }

    public static NotFoundException getUserIsNotExistsException(){
        return new NotFoundException(ErrorCodes.Constants.USER_ID_IS_NOT_EXISTS_ERROR);
    }
}
