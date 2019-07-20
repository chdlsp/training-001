package com.rasol.training001.exception;


import com.rasol.training001.code.ErrorCodes;
import org.springframework.http.HttpStatus;

public class ConflictException extends RestException{

    private ConflictException(String message){
        super(message, HttpStatus.CONFLICT);
    }

    public static ConflictException getUserAlreadyExistsException(){
        return new ConflictException(ErrorCodes.Constants.USER_ID_ALREADY_EXISTS_ERROR);
    }
}
