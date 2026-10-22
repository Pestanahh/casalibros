package com.casalibros.library.domain.ports.input;

import com.casalibros.library.domain.model.Book;

public interface UpdateBookUseCase {
    Book updateBook(Long id, Book book);
}
