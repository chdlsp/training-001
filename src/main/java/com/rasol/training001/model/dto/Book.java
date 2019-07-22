package com.rasol.training001.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rasol.training001.model.dto.querybook.NaverBook;
import com.rasol.training001.model.entity.BookEntity;
import com.rasol.training001.util.TimeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Book {
    private String title;
    private String contents;
    private String isbn;
    @JsonProperty("datetime")
    private String dateTime;
    private List<String> authors;
    private String publisher;
    private Integer price;
    @JsonProperty("sale_price")
    private Integer salePrice;
    private String thumbnail;

    public Book(){

    }

    public Book(NaverBook naverBook){
        this.setTitle(naverBook.getTitle());
        this.setContents(naverBook.getContents());
        this.setIsbn(naverBook.getIsbn());
        this.setDateTime(TimeUtils.convert_yyyyMMddToISO8601MilliPlusTime(naverBook.getDateTime()));
        this.setAuthors(Arrays.stream(naverBook.getAuthors().split("\\|")).collect(Collectors.toList()));
        this.setPublisher(naverBook.getPublisher());
        this.setPrice(naverBook.getPrice());
        this.setSalePrice(naverBook.getSalePrice());
        this.setThumbnail(naverBook.getThumbnail());
    }

    public Book(BookEntity bookEntity){
        this.setTitle(bookEntity.getTitle());
        this.setContents(bookEntity.getContents());
        this.setIsbn(String.join(" ", bookEntity.getIsbn10(), bookEntity.getIsbn13()));
        this.setDateTime(bookEntity.getDateTime());
        this.setAuthors(Arrays.stream(bookEntity.getAuthors().split("\\|")).collect(Collectors.toList()));
        this.setPublisher(bookEntity.getPublisher());
        this.setPrice(bookEntity.getPrice());
        this.setSalePrice(bookEntity.getSalePrice());
        this.setThumbnail(bookEntity.getThumbnail());
    }

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

    public String getDateTime() {
        return dateTime;
    }

    public Book setDateTime(String dateTime) {
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
