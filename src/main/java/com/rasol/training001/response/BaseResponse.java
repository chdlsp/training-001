package com.rasol.training001.response;

import java.util.Map;

public class BaseResponse {
    private String timestamp;
    private Map<String, String> errors;
    private String path;
    private String body;
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

    public String getBody() {
        return body;
    }

    protected BaseResponse setBody(String body) {
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
