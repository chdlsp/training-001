package com.rasol.training001.response;

import com.rasol.training001.util.TimeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;

public class ErrorResponse extends BaseResponse{
    public ErrorResponse(HttpStatus httpStatus, HttpServletRequest request, String message){
        setTimestamp(TimeUtils.getCurrentISO8601MilliPlusTime());
        setPath(request.getRequestURI());
        setMessage(message);
        setStatus(httpStatus.value());
        setError(httpStatus.getReasonPhrase());
    }

}
