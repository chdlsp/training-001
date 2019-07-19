package com.rasol.training001.model;

public class PopularKeyword {
    private String keyword;
    private Integer count;

    public String getKeyword() {
        return keyword;
    }

    public PopularKeyword setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public PopularKeyword setCount(Integer count) {
        this.count = count;
        return this;
    }
}
