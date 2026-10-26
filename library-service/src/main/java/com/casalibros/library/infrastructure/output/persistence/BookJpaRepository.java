package com.casalibros.library.infrastructure.output.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookJpaRepository extends JpaRepository<BookJpa, Long> {

    Optional<BookJpa> findByIsbn (String isbn);
}
