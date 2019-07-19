package com.rasol.training001.models;

import java.util.Date;
import java.util.List;

public class Books {
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

    public Books setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public Books setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public Books setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Books setDateTime(Date dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public Books setAuthors(List<String> authors) {
        this.authors = authors;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public Books setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Books setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public Books setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
        return this;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Books setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }
}
