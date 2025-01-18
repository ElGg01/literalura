package com.elgg.literalura.main;

import com.elgg.literalura.model.Author;
import com.elgg.literalura.model.Book;
import com.elgg.literalura.service.AuthorService;
import com.elgg.literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Main {
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    private final BookService bookService = new BookService();

    @Autowired
    private final AuthorService authorService = new AuthorService();

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
        System.out.println("5. Listar libros por idioma.\n");
    }

    public void chooseOption(){
        int option = -1;

        while (true) {
            showMenu();

            try {
                System.out.print("Tu opción: ");
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
                    bookService.searchBook(nameBook);
                    break;
                case 2:
                    bookService.printBooksSaved();
                    break;
                case 3:
                    authorService.printAuthorsSaved();
                    break;
                case 4:
                    System.out.println("\n" + "Ingresa un año para consultar los autores vivos: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    authorService.searchAuthorsByYear(year);
                    break;
                case 5:
                    System.out.println("\n" + "Ingresa las siglas de un lenguaje: ");
                    String language = scanner.nextLine();
                    bookService.filterBookByLang(language);
                    break;
                default:
                    System.out.println("Selecciona una opción valida.");
                    break;
            }
        }
    }
}
