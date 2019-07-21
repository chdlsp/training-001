package com.rasol.training001.service;

import com.rasol.training001.model.dto.PopularKeyword;

public interface PopularKeywordService {
    PopularKeyword createOrUpdatePopularKeyword(String keyword);
}
