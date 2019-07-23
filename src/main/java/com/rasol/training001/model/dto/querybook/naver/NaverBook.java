package com.rasol.training001.model.dto.querybook.naver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class NaverBook {
    private String title;
    @XmlElement(name = "description")
    private String contents;
    private String isbn;
    @XmlElement(name = "pubdate")
    private String dateTime;
    @XmlElement(name = "author")
    private String authors;
    private String publisher;
    private Integer price;
    @XmlElement(name = "discount")
    private Integer salePrice;
    @XmlElement(name = "image")
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
