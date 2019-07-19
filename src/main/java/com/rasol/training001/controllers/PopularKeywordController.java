package com.rasol.training001.controllers;

import com.rasol.training001.models.PopularKeywords;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/popularKeywords")
public class PopularKeywordController {

    @GetMapping
    public List<PopularKeywords> getPopluarKeywordsBest10(){

        return null;
    }

}
