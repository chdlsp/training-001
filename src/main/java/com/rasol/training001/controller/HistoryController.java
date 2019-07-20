package com.rasol.training001.controller;

import com.rasol.training001.model.dto.History;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/histories")
public class HistoryController {

    @GetMapping("/users/{id}")
    public List<History> getHistoriesOrderByDateDesc(
            @PathVariable("id") String id){

        return null;
    }
}
