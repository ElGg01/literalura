package com.elgg.literalura.main;

import com.elgg.literalura.model.Book;
import com.elgg.literalura.model.dto.BookData;
import com.elgg.literalura.model.dto.SearchResult;
import com.elgg.literalura.repository.BookRepository;
import com.elgg.literalura.service.ConvertData;
import com.elgg.literalura.service.GutendexAPI;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;


public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final ConvertData convertData = new ConvertData();
    private final GutendexAPI gutendexAPI =  new GutendexAPI();

    private final BookRepository repository;

    public Main(BookRepository repository) {
        this.repository = repository;
    }

    private void toGreet(){
        System.out.println("""
                
                Bienvenido a Literalura, selecciona una opción:
                """);
    }

    private void showMenu() {
        toGreet();
        System.out.println("Selecciona una opción:");
        System.out.println("0. Salir.");
        System.out.println("1. Buscar libros por titulo.");
        System.out.println("2. Listar libros guardados.");
        System.out.println("3. Listar autores registrados.");
        System.out.println("4. Listar autores vivos en un año especifico.");
        System.out.println("5. Listar libros por idioma.");
    }

    private Book searchBook(String nameBook) {
        System.out.println("\n" + "Buscando libro, espera...");
        String result = gutendexAPI.getData("books/?search=" + nameBook.strip()
                .replace(" ", "%20"));
        SearchResult result_books = convertData.convertData(result, SearchResult.class);

        if (result_books.results().isEmpty()) {
            return null;
        }

        BookData first_book = result_books.results().getFirst();
        return new Book(first_book);
    }

    public void chooseOption(){
        int option = -1;

        while (true) {
            showMenu();

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingresa un número entero.");
            } catch (Exception e) {
                System.out.println("Se encontro una Excepcion sin controlar");
            }

            switch (option) {
                case 0:
                    System.out.println("¡Adios!");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("\n" + "Ingresa el nombre del libro a buscar: ");
                    String nameBook = scanner.nextLine();

                    Book book = searchBook(nameBook);

                    if (book == null) {
                        System.out.println("No se ha encontrado ningun libro con el nombre " + nameBook + ".");
                        break;
                    }

                    Optional<Book> existingBook = repository.findByTitle(book.getTitle());

                    if (existingBook.isPresent()) {
                        System.out.println("\n" + "El libro " + book.getTitle() + "ya está registrado en la base de datos.");
                    } else {
                        System.out.println(book);
                        repository.save(book);
                        System.out.println("Libro guardado correctamente.");
                    }
                    break;
                case 2:
                    System.out.println("2");
                    System.out.println(repository.findAllByOrderByIdAsc());
                    break;
                case 3:
                    System.out.println("3");
                    break;
                case 4:
                    System.out.println("4");
                    break;
                case 5:
                    System.out.println("5");
                    break;
                default:
                    System.out.println("Selecciona una opción valida.");
                    break;
            }
        }
    }
}
