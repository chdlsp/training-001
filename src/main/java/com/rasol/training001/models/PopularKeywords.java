package com.rasol.training001.models;

public class PopularKeywords {
    private String keyword;
    private Integer count;

    public String getKeyword() {
        return keyword;
    }

    public PopularKeywords setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public PopularKeywords setCount(Integer count) {
        this.count = count;
        return this;
    }
}
