package com.elgg.literalura.model;

import com.elgg.literalura.model.dto.AuthorData;
import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_name", unique = true)
    private String authorName;

    @Column(name = "birth_year")
    private int birthYear;

    @Column(name = "death_year")
    private int deathYear;

    public Author() {}

    public Author(AuthorData authorData) {
        this.authorName = authorData.name();
        this.birthYear = authorData.birthYear();
        this.deathYear = authorData.deathYear();
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getBirthYear() {
        return birthYear;
    }


    public int getDeathYear() {
        return deathYear;
    }

    @Override
    public String toString() {
        return "\n" + "--- AUTOR ---" + "\n" +
                "Nombre: " + authorName + '\n' +
                "Fecha de nacimiento: " + birthYear + '\n' +
                "Fecha de muerte: " + deathYear + '\n' +
                "------";
    }
}
