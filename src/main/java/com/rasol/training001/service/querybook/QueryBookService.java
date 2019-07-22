package com.rasol.training001.service.querybook;

import com.rasol.training001.model.dto.Book;

import java.util.List;

public interface QueryBookService {
    List<Book> getBookListByKeywordAndPageAndSizeAndTarget(String keyword, Integer page, Integer size);

    Book getBookByIsbn(String isbn);
}
