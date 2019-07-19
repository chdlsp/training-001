package com.rasol.training001.model;

import java.util.Date;

public class History {
    private String searcherId;
    private String keyword;
    private Date date;

    public String getSearcherId() {
        return searcherId;
    }

    public History setSearcherId(String searcherId) {
        this.searcherId = searcherId;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public History setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public History setDate(Date date) {
        this.date = date;
        return this;
    }
}
