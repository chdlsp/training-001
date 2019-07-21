package com.rasol.training001.model.response;

import com.rasol.training001.model.dto.Book;

import java.util.List;

public class SimpleBook {
    private String title;
    private String isbn;
    private String thumbnail;
    private String contents;
    private List<String> authors;
    private String dateTime;

    public SimpleBook(Book book){
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.thumbnail = book.getThumbnail();
        this.contents = book.getContents();
        this.authors = book.getAuthors();
        this.dateTime = book.getDateTime();
    }

    public String getTitle() {
        return title;
    }

    public SimpleBook setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public SimpleBook setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public SimpleBook setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public SimpleBook setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public SimpleBook setAuthors(List<String> authors) {
        this.authors = authors;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public SimpleBook setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
