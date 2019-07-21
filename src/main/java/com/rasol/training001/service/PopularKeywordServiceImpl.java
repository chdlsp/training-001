package com.rasol.training001.service;

import com.rasol.training001.model.dto.PopularKeyword;
import com.rasol.training001.model.entity.PopularKeywordEntity;
import com.rasol.training001.repository.PopularKeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PopularKeywordServiceImpl implements PopularKeywordService {
    private final PopularKeywordRepository popularKeywordRepository;

    @Autowired
    public PopularKeywordServiceImpl(PopularKeywordRepository popularKeywordRepository){
        this.popularKeywordRepository = popularKeywordRepository;
    }

    @Override
    public PopularKeyword createOrUpdatePopularKeyword(String keyword){
        PopularKeywordEntity popularKeywordEntity = popularKeywordRepository.findById(keyword).orElseGet(() -> new PopularKeywordEntity(keyword)).incrementCount();

        popularKeywordRepository.save(popularKeywordEntity);

        return new PopularKeyword(popularKeywordEntity);
    }

    @Override
    public List<PopularKeyword> getPopularKeywordListOrderByCountDesc(){
        return popularKeywordRepository.findAllByOrderByCountDesc()
                .stream()
                .map(PopularKeyword::new)
                .collect(Collectors.toList());
    }
}
