package com.elgg.literalura.service;

import com.elgg.literalura.model.Author;
import com.elgg.literalura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public void printAuthorsSaved() {
        List<Author> authors = authorRepository.findAll();

        System.out.println("Autores registrados: ");

        authors.forEach(System.out::println);
    }

    public void searchAuthorsByYear(int yearToSearch) {
        Optional<Author> authors = authorRepository.findByBirthYear(yearToSearch);

        authors.ifPresent(System.out::println);

        if (authors.isEmpty()) {
            System.out.println("No hay autores vivos en el año " + yearToSearch);
        }
    }
}
