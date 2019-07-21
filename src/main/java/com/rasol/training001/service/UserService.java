package com.rasol.training001.service;

import com.rasol.training001.model.dto.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserService {
    User createUser(User user);

//    User loginUser(User user, HttpServletRequest request);

    User loginUser(User user, HttpServletRequest request, HttpSession httpSession);
}
