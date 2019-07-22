package com.rasol.training001.service;

import com.rasol.training001.externelservice.QueryBookService;
import com.rasol.training001.model.dto.Book;
import com.rasol.training001.model.response.SimpleBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    private final QueryBookService kakaoQueryBookService;
    private final QueryBookService naverQueryBookService;
    private final HistoryService historyService;
    private final PopularKeywordService popularKeywordService;

    @Autowired
    public BookServiceImpl(HistoryService historyService, PopularKeywordService popularKeywordService,
                           @Qualifier("kakaoQueryBookServiceImpl") QueryBookService kakaoQueryBookService,
                           @Qualifier("naverQueryBookServiceImpl") QueryBookService naverQueryBookService){
        this.historyService = historyService;
        this.popularKeywordService = popularKeywordService;
        this.kakaoQueryBookService = kakaoQueryBookService;
        this.naverQueryBookService = naverQueryBookService;
    }

    @Override
    public List<SimpleBook> getSimpleBookListByKeywordAndPageAndSizeAndTarget(String keyword, Integer page, Integer size){
        List<SimpleBook> simpleBookList = Optional.ofNullable(kakaoQueryBookService.getBookListByKeywordAndPageAndSizeAndTarget(keyword, page, size))
                .orElseGet(()->naverQueryBookService.getBookListByKeywordAndPageAndSizeAndTarget(keyword, page, size))
                .stream()
                .map(SimpleBook::new)
                .collect(Collectors.toList());

        historyService.createHistory(keyword);
        popularKeywordService.createOrUpdatePopularKeyword(keyword);

        return simpleBookList;
    }

    @Override
    public Book getBookByIsbn(String isbn){
        return Optional.ofNullable(kakaoQueryBookService.getBookByIsbn(isbn)).orElseGet(() -> naverQueryBookService.getBookByIsbn(isbn));
    }

//    private final List<QueryBookService> queryBookService;
//    private final HistoryService historyService;
//    private final PopularKeywordService popularKeywordService;
//
//    @Autowired
//    public BookServiceImpl(List<QueryBookService> queryBookService, HistoryService historyService, PopularKeywordService popularKeywordService){
//        this.queryBookService = queryBookService;
//        this.historyService = historyService;
//        this.popularKeywordService = popularKeywordService;
//    }
//
//    @Override
//    public List<SimpleBook> getSimpleBookListByKeywordAndPageAndSizeAndTarget(String keyword, Integer page, Integer size){
//        List<SimpleBook> simpleBookList = queryBookService.getBookListByKeywordAndPageAndSizeAndTarget(keyword, page, size)
//                .stream()
//                .map(SimpleBook::new)
//                .collect(Collectors.toList());
//
//        historyService.createHistory(keyword);
//        popularKeywordService.createOrUpdatePopularKeyword(keyword);
//
//        return simpleBookList;
//    }
//
//    @Override
//    public Book getBookByIsbn(String isbn){
//        return queryBookService.getBookByIsbn(isbn);
//    }
}
