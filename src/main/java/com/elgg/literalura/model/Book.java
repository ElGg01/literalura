package com.elgg.literalura.model;

import com.elgg.literalura.model.dto.BookData;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "download_count")
    private int downloadCount;

    @Enumerated(EnumType.STRING)
    private Language language;

    public Book() {}

    public Book(BookData bookData) {
        this.title = bookData.title();
        this.author = new Author(bookData.author().getFirst());
        this.downloadCount = bookData.dowload_count();
        this.language = Language.fromString(bookData.language().getFirst());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "\n" + "--- LIBRO ---" + "\n" +
                "Titulo: " + title + "\n" +
                "Autor: " + author.getAuthorName() + "\n" +
                "Lenguaje: " + language + "\n" +
                "Descargas: " + downloadCount + "\n" +
                "------";
    }
}
