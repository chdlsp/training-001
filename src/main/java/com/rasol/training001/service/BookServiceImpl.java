package com.rasol.training001.service;

import com.rasol.training001.exception.NotFoundException;
import com.rasol.training001.model.entity.BookEntity;
import com.rasol.training001.repository.BookRepository;
import com.rasol.training001.service.querybook.QueryBookService;
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
    private final QueryBookService internalQueryBookService;

    private final HistoryService historyService;
    private final PopularKeywordService popularKeywordService;
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(HistoryService historyService, PopularKeywordService popularKeywordService,
                           BookRepository bookRepository,
                           @Qualifier("kakaoQueryBookServiceImpl") QueryBookService kakaoQueryBookService,
                           @Qualifier("naverQueryBookServiceImpl") QueryBookService naverQueryBookService,
                           @Qualifier("internalQueryBookServiceImpl") QueryBookService internalQueryBookService){
        this.historyService = historyService;
        this.popularKeywordService = popularKeywordService;
        this.kakaoQueryBookService = kakaoQueryBookService;
        this.naverQueryBookService = naverQueryBookService;
        this.internalQueryBookService = internalQueryBookService;
        this.bookRepository = bookRepository;
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
        Book book = Optional.ofNullable(Optional.ofNullable(
                internalQueryBookService.getBookByIsbn(isbn)).orElseGet(()
                -> kakaoQueryBookService.getBookByIsbn(isbn))).orElseGet(()
                -> naverQueryBookService.getBookByIsbn(isbn));
        Optional.ofNullable(book).ifPresent(b -> bookRepository.save(new BookEntity(b)));
        return Optional.ofNullable(book).orElseThrow(NotFoundException::getBookNotFoundException);
    }

}
