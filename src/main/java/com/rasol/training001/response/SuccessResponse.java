package com.rasol.training001.response;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

public class SuccessResponse extends BaseResponse{

    public SuccessResponse(HttpServletRequest request, Object body){
        setTimestamp(Instant.now().toString());
        setPath(request.getRequestURI());
        setBody(body);
    }
}
