package com.rasol.training001.model.dto;

import com.rasol.training001.model.entity.UserEntity;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayDeque;

public class SecurityUser extends User {
    public static final String ROLE_USER = "USER";

//    public SecurityUser(com.rasol.training001.model.dto.User user){
//        super(user.getId(), user.getPassword(), new ArrayDeque<>());
//    }

    public SecurityUser(UserEntity user){
        super(user.getId(), user.getPassword(), new ArrayDeque<>());
    }
}
