package com.rasol.training001.controller;

import com.rasol.training001.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping
    public List<Book> getBooksByPage(
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page){

        return null;
    }

    @GetMapping("/{isbn}")
    public Book getBookDetail(
            @PathVariable(value = "isbn") String isbn){

        return null;

    }


}
