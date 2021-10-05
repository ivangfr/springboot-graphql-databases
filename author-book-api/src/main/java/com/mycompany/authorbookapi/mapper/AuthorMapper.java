package com.mycompany.authorbookapi.mapper;

import com.mycompany.authorbookapi.graphql.input.AuthorInput;
import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.rest.dto.AuthorResponse;
import com.mycompany.authorbookapi.rest.dto.CreateAuthorRequest;
import com.mycompany.authorbookapi.rest.dto.UpdateAuthorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AuthorMapper {

    //-- REST API

    AuthorResponse toAuthorResponse(Author author);

    Author toAuthor(CreateAuthorRequest createAuthorRequest);

    void updateAuthorFromRequest(UpdateAuthorRequest updateAuthorRequest, @MappingTarget Author author);

    //-- GraphQL

    Author toAuthor(AuthorInput authorInput);

    void updateAuthorFromRequest(AuthorInput authorInput, @MappingTarget Author author);
}
