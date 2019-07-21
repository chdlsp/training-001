package com.rasol.training001.externelservice;

import com.rasol.training001.model.dto.Book;

import java.util.List;

public interface KakaoService {
    List<Book> getBookListByKeywordAndPageAndSizeAndTarget(String keyword, Integer page, Integer size, String target);

    Book getBookByIsbn(String isbn);
}
