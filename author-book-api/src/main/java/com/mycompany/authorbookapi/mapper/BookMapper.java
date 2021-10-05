package com.mycompany.authorbookapi.mapper;

import com.mycompany.authorbookapi.graphql.input.BookInput;
import com.mycompany.authorbookapi.model.Book;
import com.mycompany.authorbookapi.rest.dto.BookResponse;
import com.mycompany.authorbookapi.rest.dto.CreateBookRequest;
import com.mycompany.authorbookapi.rest.dto.UpdateBookRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface BookMapper {

    //-- REST API

    BookResponse toBookResponse(Book book);

    Book toBook(CreateBookRequest createBookRequest);

    void updateBookFromRequest(UpdateBookRequest updateBookRequest, @MappingTarget Book book);

    //-- GraphQL

    Book toBook(BookInput bookInput);

    void updateBookFromRequest(BookInput bookInput, @MappingTarget Book book);
}
