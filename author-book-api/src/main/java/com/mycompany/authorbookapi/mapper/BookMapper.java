package com.mycompany.authorbookapi.mapper;

import com.mycompany.authorbookapi.graphql.input.BookInput;
import com.mycompany.authorbookapi.model.Book;
import com.mycompany.authorbookapi.rest.dto.BookDto;
import com.mycompany.authorbookapi.rest.dto.CreateBookDto;
import com.mycompany.authorbookapi.rest.dto.UpdateBookDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface BookMapper {

    //-- REST API

    BookDto toBookDto(Book book);

    Book toBook(CreateBookDto createBookDto);

    void updateBookFromDto(UpdateBookDto updateBookDto, @MappingTarget Book book);

    //-- GraphQL

    Book toBook(BookInput bookInput);

    void updateBookFromDto(BookInput bookInput, @MappingTarget Book book);

}
