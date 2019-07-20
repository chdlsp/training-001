package com.rasol.training001.controller;

import com.rasol.training001.model.dto.PopularKeyword;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/popularKeywords")
public class PopularKeywordController {

    @GetMapping
    public List<PopularKeyword> getPopluarKeywordsBest10(){

        return null;
    }

}
