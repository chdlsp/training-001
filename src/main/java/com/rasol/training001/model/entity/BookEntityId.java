package com.rasol.training001.model.entity;

import java.io.Serializable;

public class BookEntityId implements Serializable {
    private String isbn10;
    private String isbn13;

    public String getIsbn10() {
        return isbn10;
    }

    public BookEntityId setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
        return this;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public BookEntityId setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
        return this;
    }
}
