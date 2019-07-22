package com.rasol.training001.response;

import com.rasol.training001.util.TimeUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

public class SuccessResponse extends BaseResponse{

    public SuccessResponse(HttpServletRequest request, Object body){
        setTimestamp(TimeUtils.getCurrentISO8601MilliPlusTime());
        setPath(request.getRequestURI());
        setBody(body);
    }
}
