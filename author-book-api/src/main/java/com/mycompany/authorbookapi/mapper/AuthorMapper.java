package com.mycompany.authorbookapi.mapper;

import com.mycompany.authorbookapi.graphql.input.AuthorInput;
import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.rest.dto.AuthorDto;
import com.mycompany.authorbookapi.rest.dto.CreateAuthorDto;
import com.mycompany.authorbookapi.rest.dto.UpdateAuthorDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AuthorMapper {

    //-- REST API

    AuthorDto toAuthorDto(Author author);

    Author toAuthor(CreateAuthorDto createAuthorDto);

    void updateAuthorFromDto(UpdateAuthorDto updateAuthorDto, @MappingTarget Author author);

    //-- GraphQL

    Author toAuthor(AuthorInput authorInput);

    void updateAuthorFromDto(AuthorInput authorInput, @MappingTarget Author author);

}
