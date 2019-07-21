package com.rasol.training001.model.dto;

import com.rasol.training001.model.entity.PopularKeywordEntity;

public class PopularKeyword {
    private String keyword;
    private Long count;

    public PopularKeyword(PopularKeywordEntity popularKeywordEntity){
        this.keyword = popularKeywordEntity.getKeyword();
        this.count = popularKeywordEntity.getCount();
    }

    public String getKeyword() {
        return keyword;
    }

    public PopularKeyword setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public Long getCount() {
        return count;
    }

    public PopularKeyword setCount(Long count) {
        this.count = count;
        return this;
    }
}
