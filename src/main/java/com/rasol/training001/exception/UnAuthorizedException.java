package com.rasol.training001.exception;

import com.rasol.training001.code.ErrorCodes;
import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends RestException{
    private UnAuthorizedException(String message){
        super(message, HttpStatus.UNAUTHORIZED);
    }

    public static UnAuthorizedException getUserPasswordIsNotCorrectException(){
        return new UnAuthorizedException(ErrorCodes.Constants.USER_PASSWORD_IS_WRONG_ERROR);
    }
}
