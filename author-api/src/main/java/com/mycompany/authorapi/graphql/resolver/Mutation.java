package com.mycompany.authorapi.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.mycompany.authorapi.graphql.input.CreateAuthorInput;
import com.mycompany.authorapi.graphql.input.UpdateAuthorInput;
import com.mycompany.authorapi.graphql.service.AuthorService;
import com.mycompany.authorapi.model.Author;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    private final AuthorService authorService;
    private final MapperFacade mapperFacade;

    public Mutation(AuthorService authorService, MapperFacade mapperFacade) {
        this.authorService = authorService;
        this.mapperFacade = mapperFacade;
    }

    public Author createAuthor(CreateAuthorInput createAuthorInput) {
        Author author = mapperFacade.map(createAuthorInput, Author.class);
        return authorService.saveAuthor(author);
    }

    public Author updateAuthor(Long id, UpdateAuthorInput updateAuthorInput) {
        Author author = authorService.validateAndGetAuthor(id);
        mapperFacade.map(updateAuthorInput, author);
        return authorService.saveAuthor(author);
    }

    public Author deleteAuthor(Long id) {
        Author author = authorService.validateAndGetAuthor(id);
        authorService.deleteAuthor(author);
        return author;
    }

}
