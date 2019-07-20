package com.rasol.training001.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Map;

public class BaseResponse {
    private String timestamp;
    private Map<String, String> errors;
    private String path;
    private Object body;
    private String message;

    public String getTimestamp() {
        return timestamp;
    }

    protected BaseResponse setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    protected BaseResponse setErrors(Map<String, String> errors) {
        this.errors = errors;
        return this;
    }

    public String getPath() {
        return path;
    }

    protected BaseResponse setPath(String path) {
        this.path = path;
        return this;
    }

    public Object getBody() {
        return body;
    }

    public BaseResponse setBody(Object body) {
        this.body = body;
        return this;
    }

    public String getMessage() {
        return message;
    }

    protected BaseResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
