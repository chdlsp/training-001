package com.rasol.training001.controllers;

import com.rasol.training001.models.Books;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping
    public List<Books> getBooksByPage(
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page){

        return null;
    }

    @GetMapping("/{isbn}")
    public Books getBookDetail(
            @PathVariable(value = "isbn") String isbn){

        return null;

    }


}
