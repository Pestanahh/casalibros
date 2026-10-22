package com.casalibros.library.domain.ports.input;

import com.casalibros.library.domain.model.Book;

public interface GetBookByIdUseCase {
    Book getBookById(Long id);
}
