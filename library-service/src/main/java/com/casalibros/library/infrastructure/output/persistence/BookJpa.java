package com.casalibros.library.infrastructure.output.persistence;

import com.casalibros.library.domain.model.Origin;
import com.casalibros.library.domain.model.ReadingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// Not using @Data on purpose: its equals/hashCode use all fields (including the
// generated id), which causes problems with JPA entities. Use @Getter/@Setter instead.
public class BookJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private String genre;

    @Enumerated(EnumType.STRING)
    private ReadingStatus readingStatus;

    @Enumerated(EnumType.STRING)
    private Origin origin;

    private Integer rating;
}
