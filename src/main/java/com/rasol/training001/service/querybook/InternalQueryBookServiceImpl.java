package com.rasol.training001.service.querybook;

import com.rasol.training001.model.dto.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternalQueryBookServiceImpl implements QueryBookService{

    @Override
    public List<Book> getBookListByKeywordAndPageAndSizeAndTarget(String keyword, Integer page, Integer size) {
        return null;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return null;
    }
}
