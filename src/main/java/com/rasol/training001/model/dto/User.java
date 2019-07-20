package com.rasol.training001.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {
    @Size(max=255, message = "max length of id is 255")
    @NotBlank(message = "id is mandatory")
    private String id;

    @NotBlank(message = "password is mandatory")
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
