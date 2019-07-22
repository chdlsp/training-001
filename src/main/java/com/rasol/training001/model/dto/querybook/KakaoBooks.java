package com.rasol.training001.model.dto.querybook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rasol.training001.model.dto.Book;

import java.util.ArrayList;
import java.util.List;

public class KakaoBooks {
    @JsonProperty("documents")
    private List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public KakaoBooks setBooks(List<Book> books) {
        this.books = books;
        return this;
    }
}
