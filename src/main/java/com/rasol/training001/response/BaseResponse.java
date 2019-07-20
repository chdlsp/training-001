package com.rasol.training001.response;

import java.util.Map;

public class BaseResponse {
    private String timestamp;
    private Integer status;
    private Map<String, String> errors;
    private String path;
    private String error;

    public String getTimestamp() {
        return timestamp;
    }

    protected BaseResponse setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    protected BaseResponse setStatus(Integer status) {
        this.status = status;
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

    public String getError() {
        return error;
    }

    protected BaseResponse setError(String error) {
        this.error = error;
        return this;
    }
}
