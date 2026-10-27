package com.casalibros.library.infrastructure.output.persistence;

import com.casalibros.library.domain.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookPersistenceMapper {

    Book toDomain(BookJpa bookJpa);
    BookJpa toJpa(Book book);
}
