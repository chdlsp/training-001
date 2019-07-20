package com.rasol.training001.service;

import com.rasol.training001.externelservice.KakaoService;
import com.rasol.training001.model.dto.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final KakaoService kakaoService;

    public BookServiceImpl(KakaoService kakaoService){
        this.kakaoService = kakaoService;
    }

    @Override
    public List<Book> getBookListByKeywordAndPageAndSizeAndTarget(String keyword, Integer page, Integer size, String target){
        return kakaoService.getBookListByKeywordAndPageAndSizeAndTarget(keyword, page, size, target);
    }
}
