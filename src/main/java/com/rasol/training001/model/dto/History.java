package com.rasol.training001.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rasol.training001.model.entity.HistoryEntity;

import java.util.Date;

public class History {
    private String userId;
    private String keyword;
    private String date;

    public History(HistoryEntity historyEntity){
        this.userId = historyEntity.getUserId();
        this.keyword = historyEntity.getUserId();
        this.date = historyEntity.getModifiedDate();
    }

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

    public String getDate() {
        return date;
    }

    public History setDate(String date) {
        this.date = date;
        return this;
    }
}
