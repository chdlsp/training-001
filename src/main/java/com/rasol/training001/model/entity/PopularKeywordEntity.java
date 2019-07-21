package com.rasol.training001.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "popular_keywords")
public class PopularKeywordEntity extends BaseEntity{
    @Id
    private String keyword;
    private Long count = 0L;

    public PopularKeywordEntity() {
    }

    public PopularKeywordEntity(String keyword){
        this.setKeyword(keyword);
    }

    public String getKeyword() {
        return keyword;
    }

    public PopularKeywordEntity setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public Long getCount() {
        return count;
    }

    public PopularKeywordEntity setCount(Long count) {
        this.count = count;
        return this;
    }

    public PopularKeywordEntity incrementCount(){
        this.count = this.count + 1;
        return this;
    }
}
