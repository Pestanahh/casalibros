package com.casalibros.library.domain.ports.input;

import com.casalibros.library.domain.model.Book;

public interface CreateBookUseCase {

    Book createBook(Book book);

}
