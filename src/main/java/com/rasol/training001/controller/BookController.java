package com.rasol.training001.controller;

import com.rasol.training001.exception.RestException;
import com.rasol.training001.model.dto.Book;
import com.rasol.training001.model.response.SimpleBook;
import com.rasol.training001.response.RestResponseEntity;
import com.rasol.training001.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public RestResponseEntity getBooks(
            @RequestParam(value = "keyword") @Valid @NotBlank String keyword,
            @RequestParam(value = "page", required = false, defaultValue = "1") @Valid @Min(1) @Max(100) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") @Valid @Min(1) @Max(50) Integer size,
            HttpServletRequest request){
        List<SimpleBook> SimpleBookList = bookService.getSimpleBookListByKeywordAndPageAndSizeAndTarget(keyword, page, size);

        return new RestResponseEntity(request, SimpleBookList);
    }

    @GetMapping("/isbns/{isbn}")
    public RestResponseEntity getBookDetail(
            @PathVariable(value = "isbn") @Valid @NotBlank String isbn,
            HttpServletRequest request){

        Book book = bookService.getBookByIsbn(isbn);

        return new RestResponseEntity(request, book);

    }


}
