package com.rasol.training001.exception;

import com.rasol.training001.constant.Constants;
import org.springframework.http.HttpStatus;

public class NotFoundException extends RestException {
    private NotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }

    public static NotFoundException getUserNotFoundException(){
        return new NotFoundException(Constants.USER_NOT_FOUND_ERROR);
    }

    public static NotFoundException getBookNotFoundException(){
        return new NotFoundException(Constants.BOOK_NOT_FOUND_ERROR);
    }
}
