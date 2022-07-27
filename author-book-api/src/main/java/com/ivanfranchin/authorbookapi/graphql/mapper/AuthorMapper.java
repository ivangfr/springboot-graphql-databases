package com.ivanfranchin.authorbookapi.graphql.mapper;

import com.ivanfranchin.authorbookapi.graphql.input.AuthorInput;
import com.ivanfranchin.authorbookapi.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        implementationName = "GraphQlAuthorMapper",
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AuthorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Author toAuthor(AuthorInput authorInput);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateAuthorFromRequest(AuthorInput authorInput, @MappingTarget Author author);
}
