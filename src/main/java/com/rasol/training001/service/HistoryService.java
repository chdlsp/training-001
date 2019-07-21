package com.rasol.training001.service;

import com.rasol.training001.model.dto.History;

import java.util.List;

public interface HistoryService {
    void createHistory(String keyword);

    List<History> getUserHistory(String userId);
}
