package com.mycompany.authorapi.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mycompany.authorapi.graphql.exception.AuthorNotFoundException;
import com.mycompany.authorapi.model.Author;
import com.mycompany.authorapi.repository.AuthorRepository;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    private final AuthorRepository authorRepository;

    public Query(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Iterable<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthor(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author to be retrieved not found", id));
    }

}
