package com.rasol.training001.controller;

import com.rasol.training001.constant.Constants;
import com.rasol.training001.exception.RestException;
import com.rasol.training001.util.ValidationUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    void checkValidRequestUser(String userId){
        if(!ValidationUtils.checkValidRequestUserId(userId)){
            throw new RestException(Constants.ACCESS_DENIED, HttpStatus.FORBIDDEN);
        }
    }

}
