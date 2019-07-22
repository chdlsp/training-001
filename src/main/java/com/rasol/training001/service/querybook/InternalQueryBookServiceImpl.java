package com.rasol.training001.service.querybook;

import com.rasol.training001.model.dto.Book;
import com.rasol.training001.model.entity.BookEntity;
import com.rasol.training001.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class InternalQueryBookServiceImpl implements QueryBookService{

    private final BookRepository bookRepository;

    @Autowired
    public InternalQueryBookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBookListByKeywordAndPageAndSizeAndTarget(String keyword, Integer page, Integer size) {
        return null;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        String isbn10 = Arrays.stream(isbn.split(" ")).filter(s -> s.length() == 10).findFirst().orElse("");
        String isbn13 = Arrays.stream(isbn.split(" ")).filter(s -> s.length() == 13).findFirst().orElse("");

        return Optional.ofNullable(bookRepository.findByIsbn10OrIsbn13(isbn10, isbn13)).map(BookEntity::getBook).orElse(null);
    }
}
