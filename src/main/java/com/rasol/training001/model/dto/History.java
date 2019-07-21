package com.rasol.training001.model.dto;

import java.util.Date;

public class History {
    private String userId;
    private String keyword;
    private Date date;

    public String getUserId() {
        return userId;
    }

    public History setUserId(String userId) {
        this.userId = userId;
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
