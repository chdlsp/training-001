package com.rasol.training001.util;

public class ValidationUtils {

    public static boolean checkValidRequestUserId(String userId){
        return Utils.getRequestUserId().equals(userId);
    }
}
