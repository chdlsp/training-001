package com.rasol.training001.service;

import com.rasol.training001.model.entity.HistoryEntity;
import com.rasol.training001.repository.HistoryRepository;
import com.rasol.training001.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService{

    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryServiceImpl(HistoryRepository historyRepository){
        this.historyRepository = historyRepository;
    }

    @Override
    public void createHistory(String keyword){
        String userId = Utils.getRequestUserId();
        historyRepository.save(historyRepository.findByUserIdAndKeyword(userId, keyword).orElseGet(() -> new HistoryEntity(userId, keyword)));
    }
}
