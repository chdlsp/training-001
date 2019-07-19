package com.rasol.training001.controllers;

import com.rasol.training001.models.Users;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping()
    public Users createUser(Users users){

        return null;

    }

    @PostMapping("/login")
    public Users loginUser(Users users){

        return null;

    }


}
