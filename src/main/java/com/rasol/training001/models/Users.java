package com.rasol.training001.models;

public class Users {
    private String id;
    private String password;

    public String getId() {
        return id;
    }

    public Users setId(String id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Users setPassword(String password) {
        this.password = password;
        return this;
    }
}
