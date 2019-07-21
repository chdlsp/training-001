package com.rasol.training001.controller;

import com.rasol.training001.code.ErrorCodes;
import com.rasol.training001.exception.RestException;
import com.rasol.training001.util.ValidationUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    protected void checkValidRequestUser(String userId){
        if(!ValidationUtils.checkValidRequestUserId(userId)){
            throw new RestException(ErrorCodes.Constants.ACCESS_DENIED, HttpStatus.FORBIDDEN);
        }
    }

}
