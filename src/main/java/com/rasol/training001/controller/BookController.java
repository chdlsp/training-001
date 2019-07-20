package com.rasol.training001.controller;

import com.rasol.training001.model.dto.Book;
import com.rasol.training001.response.RestResponseEntity;
import com.rasol.training001.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public RestResponseEntity getBooks(
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "target", required = false, defaultValue = "title") String target,
            HttpServletRequest request){

        List<Book> bookList = bookService.getBookListByKeywordAndPageAndSizeAndTarget(keyword, page, size, target);

        return new RestResponseEntity(request, bookList);
    }

    @GetMapping("/{isbn}")
    public RestResponseEntity getBookDetail(
            @PathVariable(value = "isbn") String isbn){

        return null;

    }


}
