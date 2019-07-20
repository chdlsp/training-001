package com.rasol.training001.service;

import com.rasol.training001.model.dto.User;

public interface UserService{
    User createUser(User user);

    User loginUser(User user);
}
