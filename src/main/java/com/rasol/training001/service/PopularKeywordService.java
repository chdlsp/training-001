package com.rasol.training001.service;

import com.rasol.training001.model.dto.PopularKeyword;

import java.util.List;

public interface PopularKeywordService {
    void createOrUpdatePopularKeyword(String keyword);

    List<PopularKeyword> getPopularKeywordListOrderByCountDesc();
}
