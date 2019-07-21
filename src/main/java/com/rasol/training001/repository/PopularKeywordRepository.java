package com.rasol.training001.repository;

import com.rasol.training001.model.entity.PopularKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopularKeywordRepository extends JpaRepository<PopularKeywordEntity, String> {
}
