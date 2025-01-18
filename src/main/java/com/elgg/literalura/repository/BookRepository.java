package com.elgg.literalura.repository;

import com.elgg.literalura.model.Book;
import com.elgg.literalura.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Método para encontrar un libro por título
    Optional<Book> findByTitle(String title);

    // Método para obtener todos los libros ordenados por id
    List<Book> findAllByOrderByIdAsc();

    Optional<Book> findAllByLanguage(Language language);
}
