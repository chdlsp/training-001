package com.rasol.training001.controller;

import com.rasol.training001.code.ErrorCodes;
import com.rasol.training001.model.dto.User;
import com.rasol.training001.response.RestResponseEntity;
import com.rasol.training001.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping()
    public RestResponseEntity createUser(@RequestBody @Valid User user, HttpServletRequest request){
        userService.createUser(user);
        user.setPassword(null);

        return new RestResponseEntity(request, user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody @Valid User user){
        return null;

    }


}
