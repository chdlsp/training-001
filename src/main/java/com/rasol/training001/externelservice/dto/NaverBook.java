package com.rasol.training001.externelservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NaverBook {
    private String title;
    @JsonProperty("description")
    private String contents;
    private String isbn;
    @JsonProperty("pubdate")
    private String dateTime;
    @JsonProperty("author")
    private String authors;
    private String publisher;
    private Integer price;
    @JsonProperty("discount")
    private Integer salePrice;
    @JsonProperty("image")
    private String thumbnail;

    public String getTitle() {
        return title;
    }

    public NaverBook setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public NaverBook setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public NaverBook setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public NaverBook setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getAuthors() {
        return authors;
    }

    public NaverBook setAuthors(String authors) {
        this.authors = authors;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public NaverBook setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public NaverBook setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public NaverBook setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
        return this;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public NaverBook setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }
}
