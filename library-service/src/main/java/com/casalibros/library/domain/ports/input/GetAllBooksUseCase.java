package com.casalibros.library.domain.ports.input;

import com.casalibros.library.domain.model.Book;

import java.util.List;

public interface GetAllBooksUseCase {
    List<Book> getAllBooks();
}
