package com.elgg.literalura.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        @JsonAlias("title") String title,
        @JsonAlias("languages") List<String> language,
        @JsonAlias("download_count") int dowload_count,
        @JsonAlias("authors") List<AuthorData> author
) {
}
