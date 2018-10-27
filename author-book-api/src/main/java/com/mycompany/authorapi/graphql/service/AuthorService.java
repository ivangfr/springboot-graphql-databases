package com.mycompany.authorapi.graphql.service;

import com.mycompany.authorapi.model.Author;

import java.util.Optional;

public interface AuthorService {

    Iterable<Author> getAuthors();

    Optional<Author> getAuthor(Long id);

    Author validateAndGetAuthor(Long id);

    Author saveAuthor(Author author);

    void deleteAuthor(Author author);

}
