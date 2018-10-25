package com.mycompany.authorapi.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mycompany.authorapi.graphql.service.AuthorService;
import com.mycompany.authorapi.model.Author;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    private final AuthorService authorService;

    public Query(AuthorService authorService) {
        this.authorService = authorService;
    }

    public Iterable<Author> getAuthors() {
        return authorService.getAuthors();
    }

    public Author getAuthor(Long id) {
        return authorService.validateAndGetAuthor(id);
    }

}
