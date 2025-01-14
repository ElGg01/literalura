package com.elgg.literalura.repository;

import com.elgg.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Método para encontrar un libro por título
    Optional<Book> findByTitle(String title);

    // Método para obtener todos los libros ordenados por id
    List<Book> findAllByOrderByIdAsc();
}
