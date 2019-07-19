package com.rasol.training001.controller;

import com.rasol.training001.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping()
    public User createUser(User user){

        return null;

    }

    @PostMapping("/login")
    public User loginUser(User user){

        return null;

    }


}
