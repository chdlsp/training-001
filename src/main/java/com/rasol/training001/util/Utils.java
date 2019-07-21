package com.rasol.training001.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {

    public static String getRequestUserId(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
