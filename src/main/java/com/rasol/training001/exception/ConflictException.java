package com.rasol.training001.exception;


import com.rasol.training001.constant.Constants;
import org.springframework.http.HttpStatus;

public class ConflictException extends RestException{

    private ConflictException(String message){
        super(message, HttpStatus.CONFLICT);
    }

    public static ConflictException getUserAlreadyExistsException(){
        return new ConflictException(Constants.USER_ID_ALREADY_EXISTS_ERROR);
    }
}
