package com.casalibros.library.domain.ports.output;

import com.casalibros.library.domain.model.Book;

import java.util.List;
import java.util.Optional;

public interface LibraryRepositoryPort {

    List<Book> findAllBooks();
    Optional<Book> findBookById(Long id);
    Book saveBook(Book book);
    void deleteBookById(Long id);
    Optional<Book> findBookByIsbn(String isbn);
}
