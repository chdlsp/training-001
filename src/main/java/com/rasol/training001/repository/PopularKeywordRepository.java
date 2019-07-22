package com.rasol.training001.repository;

import com.rasol.training001.model.entity.PopularKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PopularKeywordRepository extends JpaRepository<PopularKeywordEntity, String> {

    List<PopularKeywordEntity> findAllByOrderByCountDesc();

    // for popularKeywords.count's concurrency problem
    @Modifying
    @Transactional
    @Query("UPDATE PopularKeywordEntity p SET p.count = p.count + 1L WHERE p.keyword = :keyword")
    Integer increaseCount(@Param("keyword") String keyword);
}
