package com.rasol.training001.service;

import com.rasol.training001.externelservice.KakaoService;
import com.rasol.training001.model.dto.Book;
import com.rasol.training001.model.response.SimpleBook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    private final KakaoService kakaoService;

    public BookServiceImpl(KakaoService kakaoService){
        this.kakaoService = kakaoService;
    }

    @Override
    public List<SimpleBook> getSimpleBookListByKeywordAndPageAndSizeAndTarget(String keyword, Integer page, Integer size, String target){
        return kakaoService.getBookListByKeywordAndPageAndSizeAndTarget(keyword, page, size, target)
                .stream()
                .map(SimpleBook::new)
                .collect(Collectors.toList());
    }

    @Override
    public Book getBookByIsbn(String isbn){
        return kakaoService.getBookByIsbn(isbn);
    }
}
