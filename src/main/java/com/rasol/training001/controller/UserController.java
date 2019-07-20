package com.rasol.training001.controller;

import com.rasol.training001.model.dto.User;
import com.rasol.training001.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping()
    public User createUser(@RequestBody @Valid User user){
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody @Valid User user){
        return null;

    }


}
