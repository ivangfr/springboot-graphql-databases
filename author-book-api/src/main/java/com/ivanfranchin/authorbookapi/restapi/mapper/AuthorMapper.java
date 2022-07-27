package com.ivanfranchin.authorbookapi.restapi.mapper;

import com.ivanfranchin.authorbookapi.model.Author;
import com.ivanfranchin.authorbookapi.restapi.dto.AuthorResponse;
import com.ivanfranchin.authorbookapi.restapi.dto.CreateAuthorRequest;
import com.ivanfranchin.authorbookapi.restapi.dto.UpdateAuthorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        implementationName = "RestApiAuthorMapper",
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AuthorMapper {

    AuthorResponse toAuthorResponse(Author author);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Author toAuthor(CreateAuthorRequest createAuthorRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateAuthorFromRequest(UpdateAuthorRequest updateAuthorRequest, @MappingTarget Author author);
}
