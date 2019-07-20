package com.rasol.training001.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException{


    private ConflictException(String message){
        super(message);
    }


    public static ConflictException getUserAlreadyExistsException(String id){
        return new ConflictException("user already exists, id : " + id);
    }
}
