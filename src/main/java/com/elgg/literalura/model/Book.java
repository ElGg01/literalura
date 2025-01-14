package com.elgg.literalura.model;

import com.elgg.literalura.model.dto.BookData;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String title;

    private List<String> languages;

    private int dowload_count;

    public Book(BookData bookData) {
        this.title = bookData.title();
        this.languages = bookData.languages();
        this.dowload_count = bookData.dowload_count();
    }

    public Book() {}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getDowload_count() {
        return dowload_count;
    }

    public void setDowload_count(int dowload_count) {
        this.dowload_count = dowload_count;
    }

    @Override
    public String toString() {
        return "\n" + "--- LIBRO ENCONTRADO ---" + "\n" +
                "Titulo: " + title + "\n" +
                "Lenguajes: " + String.join(", ", languages) + "\n" +
                "Descargas: " + dowload_count + "\n" +
                "------------------------" + "\n";
    }
}
