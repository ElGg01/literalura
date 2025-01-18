package com.elgg.literalura.service;

import com.elgg.literalura.model.Book;
import com.elgg.literalura.model.Language;
import com.elgg.literalura.model.dto.SearchResult;
import com.elgg.literalura.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    private final ConvertData convertData = new ConvertData();
    private final GutendexAPI gutendexAPI =  new GutendexAPI();

    public void printBooksSaved() {
        List<Book> booksSaved = bookRepository.findAllByOrderByIdAsc();
        booksSaved.forEach(System.out::println);
    }

    @Transactional
    public void searchBook(String nameBook) {
        System.out.println("\n" + "Buscando libro, espera...");
        String result = gutendexAPI.getData("books/?search=" + nameBook.strip()
                .replace(" ", "%20"));
        SearchResult result_books = convertData.convertData(result, SearchResult.class);

        Book book = new Book(result_books.results().getFirst());

        System.out.println("¡Libro encontrado!");

        Optional<Book> existingBook = bookRepository.findByTitle(nameBook);

        if (existingBook.isPresent()) {
            System.out.println("\n" + "El libro " + book.getTitle() + " ya está registrado en la base de datos.");
        } else {
            System.out.println(book);
            try {
                bookRepository.save(book);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("Libro guardado correctamente.");
        }
    }

    public void filterBookByLang(String language) {
        try {
            Optional<Book> booksOfLanguage = bookRepository.findAllByLanguage(Language.fromString(language));
            booksOfLanguage.ifPresent(System.out::println);

            if (booksOfLanguage.isEmpty()){
                System.out.println("No hay libros en ese idioma");
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error no controlado");
        }
    }
}
