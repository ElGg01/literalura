package com.elgg.literalura.repository;

import com.elgg.literalura.model.Author;
import com.elgg.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<String> findByAuthorName(String authorName);

    @Query("SELECT a FROM Author a WHERE :year BETWEEN a.birthYear AND COALESCE(a.deathYear, :year)")
    Optional<Author> findByBirthYear(@Param("year") int yearToSearch);
}
