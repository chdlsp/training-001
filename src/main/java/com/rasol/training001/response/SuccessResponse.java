package com.rasol.training001.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.Map;

public class SuccessResponse extends BaseResponse{
    public SuccessResponse(HttpStatus status, WebRequest request, Map<String, String> errors){
        setTimestamp(Instant.now().toString());
        setStatus(status.value());
        setError(status.name());
        setPath(((ServletWebRequest)request).getRequest().getRequestURI());
        setErrors(errors);
    }
}
