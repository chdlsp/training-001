package com.rasol.training001.repository;

import com.rasol.training001.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, String> {

    BookEntity findByIsbn10OrIsbn13(String isbn10, String isbn13);

    List<BookEntity> findAllByModifiedDateBefore(String cachingTimeOver);
}
