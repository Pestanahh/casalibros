package com.casalibros.library.infrastructure.output.persistence;

import com.casalibros.library.domain.model.Book;
import com.casalibros.library.domain.ports.output.LibraryRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookPersistenceAdapter implements LibraryRepositoryPort {

    private final BookJpaRepository bookJpaRepository;
    private final BookPersistenceMapper bookPersistenceMapper;

    @Override
    public List<Book> findAllBooks() {
        // Convert every JPA entity to a domain model
        return bookJpaRepository.findAll().stream()
                .map(bookPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return bookJpaRepository.findById(id)
                .map(bookPersistenceMapper::toDomain);
    }

    @Override
    public Book saveBook(Book book) {
        // Domain -> JPA -> save -> back to domain (the saved entity has the generated id)
        BookJpa saved = bookJpaRepository.save(bookPersistenceMapper.toJpa(book));
        return bookPersistenceMapper.toDomain(saved);
    }

    @Override
    public void deleteBookById(Long id) {
        bookJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Book> findBookByIsbn(String isbn) {
        return bookJpaRepository.findByIsbn(isbn)
                .map(bookPersistenceMapper::toDomain);
    }
}
