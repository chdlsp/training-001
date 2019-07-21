package com.rasol.training001.service;

import com.rasol.training001.model.dto.History;
import com.rasol.training001.model.entity.HistoryEntity;
import com.rasol.training001.model.response.SimpleBook;
import com.rasol.training001.repository.HistoryRepository;
import com.rasol.training001.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<History> getUserHistory(String userId){
        return historyRepository.findAllByUserIdOrderByModifiedDateDesc(userId)
                .stream()
                .map(History::new)
                .collect(Collectors.toList());
    }
}
