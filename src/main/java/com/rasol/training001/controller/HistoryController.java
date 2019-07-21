package com.rasol.training001.controller;

import com.rasol.training001.exception.RestException;
import com.rasol.training001.model.dto.History;
import com.rasol.training001.response.RestResponseEntity;
import com.rasol.training001.service.HistoryService;
import com.rasol.training001.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/histories")
public class HistoryController extends BaseController{

    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService){
        this.historyService = historyService;
    }

    @GetMapping("/users/{userId}")
    public RestResponseEntity getHistoriesOrderByDateDesc(
            @PathVariable("userId") String userId,
            HttpServletRequest request){
        checkValidRequestUser(userId);

        List<History> historyList = historyService.getUserHistory(userId);

        return new RestResponseEntity(request, historyList);
    }
}
