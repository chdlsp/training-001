package com.rasol.training001.controllers;

import com.rasol.training001.models.Histories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/histories")
public class HistoryController {

    @GetMapping("/users/{id}")
    public List<Histories> getHistoriesOrderByDateDesc(
            @PathVariable("id") String id){

        return null;
    }
}
