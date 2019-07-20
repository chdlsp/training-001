package com.rasol.training001.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class KakaoBook {
    @JsonProperty("documents")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public KakaoBook setBooks(List<Book> books) {
        this.books = books;
        return this;
    }
}
