package com.rasol.training001.model;

import java.util.Date;
import java.util.List;

public class Book {
    private String title;
    private String contents;
    private String isbn;
    private Date dateTime;
    private List<String> authors;
    private String publisher;
    private Integer price;
    private Integer salePrice;
    private String thumbnail;

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public Book setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Book setDateTime(Date dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public Book setAuthors(List<String> authors) {
        this.authors = authors;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public Book setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Book setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public Book setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
        return this;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Book setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }
}
