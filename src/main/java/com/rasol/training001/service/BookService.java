package com.rasol.training001.service;

import com.rasol.training001.model.dto.Book;

import java.util.List;

public interface BookService {
    List<Book> getBookListByKeywordAndPageAndSizeAndTarget(String keyword, Integer page, Integer size, String target);
}
