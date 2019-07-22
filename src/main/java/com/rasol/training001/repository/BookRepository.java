package com.rasol.training001.repository;

import com.rasol.training001.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, String> {

}
