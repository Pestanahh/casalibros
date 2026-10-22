package com.casalibros.library.application.service;

import com.casalibros.library.domain.exception.BookNotFoundException;
import com.casalibros.library.domain.exception.DuplicateBookException;
import com.casalibros.library.domain.model.Book;
import com.casalibros.library.domain.ports.input.*;
import com.casalibros.library.domain.ports.output.LibraryRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService implements CreateBookUseCase, DeleteBookByIdUseCase, GetBookByIdUseCase, GetAllBooksUseCase, UpdateBookUseCase {

    private final LibraryRepositoryPort libraryRepositoryPort;


    @Override
    public Book createBook(Book book) {

        if(book.getIsbn() != null && !book.getIsbn().isBlank() && libraryRepositoryPort.findBookByIsbn(book.getIsbn()).isPresent())
            throw new DuplicateBookException(book.getIsbn());

        return libraryRepositoryPort.saveBook(book);

    }

    @Override
    public void deleteBookById(Long id) {

        if(!libraryRepositoryPort.findBookById(id).isPresent())
            throw new BookNotFoundException(id);

        libraryRepositoryPort.deleteBookById(id);
    }

    @Override
    public List<Book> getAllBooks() {

        return libraryRepositoryPort.findAllBooks();
    }

    @Override
    public Book getBookById(Long id) {

        return libraryRepositoryPort.findBookById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Book updateBook(Long id, Book book) {

        // Fail fast with a 404 if the book to update does not exist
        getBookById(id);

        // Only check for duplicates when the book has a real ISBN (not null, not blank)
        if (book.getIsbn() != null && !book.getIsbn().isBlank()) {
            libraryRepositoryPort.findBookByIsbn(book.getIsbn())
                    // Ignore the book being updated: keeping its own ISBN is not a duplicate
                    .filter(found -> !found.getId().equals(id))
                    // Any book left here is a different one with the same ISBN -> 409
                    .ifPresent(found -> {
                        throw new DuplicateBookException(book.getIsbn());
                    });
        }

        // Force the id from the path so that save() updates instead of creating
        book.setId(id);
        return libraryRepositoryPort.saveBook(book);
    }
}
