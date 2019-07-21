package com.rasol.training001.controller;

import com.rasol.training001.exception.RestException;
import com.rasol.training001.model.dto.Book;
import com.rasol.training001.model.response.SimpleBook;
import com.rasol.training001.response.RestResponseEntity;
import com.rasol.training001.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public RestResponseEntity getBooks(
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            HttpServletRequest request){
        List<SimpleBook> SimpleBookList = bookService.getSimpleBookListByKeywordAndPageAndSizeAndTarget(keyword, page, size);

        return new RestResponseEntity(request, SimpleBookList);
    }

    @GetMapping("/isbns/{isbn}")
    public RestResponseEntity getBookDetail(
            @PathVariable(value = "isbn") String isbn,
            HttpServletRequest request){

        Book book = bookService.getBookByIsbn(isbn);

        return new RestResponseEntity(request, book);

    }


}
