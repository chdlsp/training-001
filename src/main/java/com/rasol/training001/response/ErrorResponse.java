package com.rasol.training001.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Map;

public class ErrorResponse extends BaseResponse{
    public ErrorResponse(WebRequest request, String message){
        setTimestamp(Instant.now().toString());
        setPath(((ServletWebRequest)request).getRequest().getRequestURI());
        setMessage(message);
    }

    public ErrorResponse(HttpStatus httpStatus, HttpServletRequest request, String message){
        setTimestamp(Instant.now().toString());
        setPath(request.getRequestURI());
        setMessage(message);
        setStatus(httpStatus.value());
        setError(httpStatus.getReasonPhrase());
    }

}
