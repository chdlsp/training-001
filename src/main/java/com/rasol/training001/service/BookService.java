package com.rasol.training001.service;

import com.rasol.training001.model.dto.Book;
import com.rasol.training001.model.response.SimpleBook;

import java.util.List;

public interface BookService {
    List<SimpleBook> getSimpleBookListByKeywordAndPageAndSizeAndTarget(String keyword, Integer page, Integer size, String target);

    Book getBookByIsbn(String isbn);
}
