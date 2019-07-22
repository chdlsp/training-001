package com.rasol.training001.model.entity;

import com.rasol.training001.model.dto.Book;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "books")
@IdClass(BookEntityId.class)
public class BookEntity extends BaseEntity{
    @Id
    private String isbn10 = "";
    @Id
    private String isbn13 = "";
    private String title;
    private String contents;
    private String dateTime;
    private String authors;
    private String publisher;
    private Integer price;
    private Integer salePrice;
    private String thumbnail;

    public BookEntity() {
    }

    public BookEntity(Book book){
        this.setIsbn10(book.getIsbn().replaceAll("<b>","").replaceAll("</b>","").split(" ")[0]);
        this.setIsbn13(book.getIsbn().replaceAll("<b>","").replaceAll("</b>","").split(" ")[1]);
        this.setTitle(book.getTitle());
        this.setContents(book.getContents());
        this.setDateTime(book.getDateTime());
        this.setAuthors(String.join("\\|", book.getAuthors()));
        this.setPublisher(book.getPublisher());
        this.setPrice(book.getPrice());
        this.setSalePrice(book.getSalePrice());
        this.setThumbnail(book.getThumbnail());
    }

    public Book getBook(){
        return new Book(this);
    }

    public String getIsbn10() {
        return isbn10;
    }

    public BookEntity setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
        return this;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public BookEntity setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public BookEntity setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public BookEntity setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getAuthors() {
        return authors;
    }

    public BookEntity setAuthors(String authors) {
        this.authors = authors;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public BookEntity setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public BookEntity setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public BookEntity setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
        return this;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public BookEntity setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }
}
