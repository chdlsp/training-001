package com.rasol.training001.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {
    @Size(max=255)
    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    public String getUserId() {
        return userId;
    }

    public User setUserId(String userId) {
        this.userId = userId;
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
