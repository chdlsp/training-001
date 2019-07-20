package com.rasol.training001.model.dto;

import com.rasol.training001.code.ErrorCodes;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {
    @Size(max=255, message = ErrorCodes.Constants.USER_ID_MAX_LENGTH_ERROR)
    @NotBlank(message = ErrorCodes.Constants.USER_ID_MANDATORY_ERROR)
    private String id;

    @NotBlank(message = ErrorCodes.Constants.USER_PASSWORD_MANDATORY_ERROR)
    private String password;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
}
