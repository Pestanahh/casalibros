package com.casalibros.library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok: generates getters, setters, toString(), equals(), hashCode()
@NoArgsConstructor // Lombok: generates empty constructor
@AllArgsConstructor // Lombok: generates constructor with all parameters
public class Book {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private ReadingStatus readingStatus;
    private Origin origin;
    private Integer rating;
}
