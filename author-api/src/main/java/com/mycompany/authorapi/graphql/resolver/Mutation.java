package com.mycompany.authorapi.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.mycompany.authorapi.graphql.input.AuthorInput;
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

    public Author createAuthor(AuthorInput authorInput) {
        Author author = mapperFacade.map(authorInput, Author.class);
        return authorRepository.save(author);
    }

}
