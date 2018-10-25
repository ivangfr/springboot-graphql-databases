package com.mycompany.authorapi.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.mycompany.authorapi.graphql.exception.AuthorNotFoundException;
import com.mycompany.authorapi.graphql.input.CreateAuthorInput;
import com.mycompany.authorapi.graphql.input.UpdateAuthorInput;
import com.mycompany.authorapi.model.Author;
import com.mycompany.authorapi.repository.AuthorRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    private final AuthorRepository authorRepository;
    private final MapperFacade mapperFacade;

    public Mutation(AuthorRepository authorRepository, MapperFacade mapperFacade) {
        this.authorRepository = authorRepository;
        this.mapperFacade = mapperFacade;
    }

    public Author createAuthor(CreateAuthorInput createAuthorInput) {
        Author author = mapperFacade.map(createAuthorInput, Author.class);
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, UpdateAuthorInput updateAuthorInput) {
        Author author = validateAndGetAuthor(id);
        mapperFacade.map(updateAuthorInput, author);
        return authorRepository.save(author);
    }

    public Author deleteAuthor(Long id) {
        Author author = validateAndGetAuthor(id);
        authorRepository.delete(author);
        return author;
    }

    private Author validateAndGetAuthor(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author not found", id));
    }

}
