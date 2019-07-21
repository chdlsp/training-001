package com.rasol.training001.service;

import com.rasol.training001.externelservice.KakaoService;
import com.rasol.training001.model.dto.Book;
import com.rasol.training001.model.response.SimpleBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    private final KakaoService kakaoService;
    private final HistoryService historyService;
    private final PopularKeywordService popularKeywordService;

    @Autowired
    public BookServiceImpl(KakaoService kakaoService, HistoryService historyService, PopularKeywordService popularKeywordService){
        this.kakaoService = kakaoService;
        this.historyService = historyService;
        this.popularKeywordService = popularKeywordService;
    }

    @Override
    public List<SimpleBook> getSimpleBookListByKeywordAndPageAndSizeAndTarget(String keyword, Integer page, Integer size){
        List<SimpleBook> simpleBookList = kakaoService.getBookListByKeywordAndPageAndSizeAndTarget(keyword, page, size)
                .stream()
                .map(SimpleBook::new)
                .collect(Collectors.toList());

        historyService.createHistory(keyword);
        popularKeywordService.createOrUpdatePopularKeyword(keyword);

        return simpleBookList;
    }

    @Override
    public Book getBookByIsbn(String isbn){
        return kakaoService.getBookByIsbn(isbn);
    }
}
