package com.rasol.training001.controller;

import com.rasol.training001.model.dto.PopularKeyword;
import com.rasol.training001.response.RestResponseEntity;
import com.rasol.training001.service.PopularKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/popularKeywords")
public class PopularKeywordController {

    private final PopularKeywordService popularKeywordService;

    @Autowired
    public PopularKeywordController(PopularKeywordService popularKeywordService){
        this.popularKeywordService = popularKeywordService;
    }

    @GetMapping
    public RestResponseEntity getPopluarKeywordsBest10(HttpServletRequest request){
        List<PopularKeyword> popularKeywordList = popularKeywordService.getPopularKeywordListOrderByCountDesc();

        return new RestResponseEntity(request, popularKeywordList);
    }

}
