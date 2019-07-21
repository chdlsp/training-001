package com.rasol.training001.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "histories")
@IdClass(HistoryEntityId.class)
public class HistoryEntity extends BaseEntity{

    @Id
    private String userId;
    @Id
    private String keyword;

    public HistoryEntity() {
    }

    public HistoryEntity(String userId, String keyword) {
        this.userId = userId;
        this.keyword = keyword;
    }

    public String getUserId() {
        return userId;
    }

    public HistoryEntity setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public HistoryEntity setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }
}
