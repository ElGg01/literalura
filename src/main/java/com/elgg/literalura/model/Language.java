package com.elgg.literalura.model;

import java.util.Arrays;

public enum Language {
    ESPAÑOL("es", "spamish", "español"),
    INGLES("en", "english", "ingles");

    private final String[] languages;

    Language(String... languages) {
        this.languages = languages;
    }

    public static Language fromString(String text) {

        return Arrays.stream(values())
                .filter(lang -> Arrays.stream(lang.languages)
                        .anyMatch(alias -> alias.equalsIgnoreCase(text)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Idioma no válido: " + text));
    }
}
