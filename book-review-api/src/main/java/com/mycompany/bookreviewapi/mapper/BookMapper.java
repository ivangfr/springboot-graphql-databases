package com.mycompany.bookreviewapi.mapper;

import com.mycompany.bookreviewapi.input.BookInput;
import com.mycompany.bookreviewapi.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface BookMapper {

    Book toBook(BookInput bookInput);

    void updateBookFromInput(BookInput bookInput, @MappingTarget Book book);
}
