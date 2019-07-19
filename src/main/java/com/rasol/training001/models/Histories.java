package com.rasol.training001.models;

import java.util.Date;

public class Histories {
    private String searcherId;
    private String keyword;
    private Date date;

    public String getSearcherId() {
        return searcherId;
    }

    public Histories setSearcherId(String searcherId) {
        this.searcherId = searcherId;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public Histories setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Histories setDate(Date date) {
        this.date = date;
        return this;
    }
}
