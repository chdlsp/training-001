package com.rasol.training001.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

public class SuccessResponse extends BaseResponse{
    private static final ObjectMapper mapper = new ObjectMapper();

    public SuccessResponse(HttpServletRequest request, Object body){
        setTimestamp(Instant.now().toString());
        setStatus(HttpStatus.OK.value());
        setPath(request.getRequestURI());
        try {
            setBody(mapper.writeValueAsString(body));
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
