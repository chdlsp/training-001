package com.rasol.training001.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class KakaoBook {
    @JsonProperty("documents")
    private List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public KakaoBook setBooks(List<Book> books) {
        this.books = books;
        return this;
    }
}
