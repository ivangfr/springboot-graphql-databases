package com.mycompany.authorapi.graphql.service;

import com.mycompany.authorapi.model.Author;

public interface AuthorService {

    Iterable<Author> getAllAuthors();

    Author validateAndGetAuthorById(Long id);

    Author saveAuthor(Author author);

    void deleteAuthor(Author author);

}
