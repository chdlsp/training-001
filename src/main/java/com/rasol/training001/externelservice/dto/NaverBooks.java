package com.rasol.training001.externelservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rasol.training001.model.dto.Book;

import java.util.ArrayList;
import java.util.List;

public class NaverBooks {
    @JsonProperty("items")
    private List<NaverBook> books = new ArrayList<>();

    public List<NaverBook> getBooks() {
        return books;
    }

    public NaverBooks setBooks(List<NaverBook> naverBooksList) {
        this.books = naverBooksList;
        return this;
    }




}
