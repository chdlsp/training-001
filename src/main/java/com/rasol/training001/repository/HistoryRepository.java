package com.rasol.training001.repository;

import com.rasol.training001.model.entity.HistoryEntity;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<HistoryEntity, String> {
    Optional<HistoryEntity> findByUserIdAndKeyword(String userId, String keyword);

    List<HistoryEntity> findAllByUserIdOrderByModifiedDateDesc(String userId);
}
