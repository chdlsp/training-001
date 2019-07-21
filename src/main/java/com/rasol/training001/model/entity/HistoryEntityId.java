package com.rasol.training001.model.entity;

import java.io.Serializable;

public class HistoryEntityId implements Serializable {
    private String userId;
    private String keyword;

    public String getUserId() {
        return userId;
    }

    public HistoryEntityId setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public HistoryEntityId setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }
}
