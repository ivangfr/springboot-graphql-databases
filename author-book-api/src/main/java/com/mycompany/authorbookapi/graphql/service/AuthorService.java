package com.mycompany.authorbookapi.graphql.service;

import com.mycompany.authorbookapi.model.Author;

public interface AuthorService {

    Iterable<Author> getAllAuthors();

    Author validateAndGetAuthorById(Long id);

    Author saveAuthor(Author author);

    void deleteAuthor(Author author);

}
