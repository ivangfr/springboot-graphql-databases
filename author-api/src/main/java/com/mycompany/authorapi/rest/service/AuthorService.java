package com.mycompany.authorapi.rest.service;

import com.mycompany.authorapi.model.Author;

import java.util.Optional;

public interface AuthorService {

    Optional<Author> getAuthor(Long id);

    Author validateAndGetAuthor(Long id);

    Author saveAuthor(Author author);

    void deleteAuthor(Author author);

}
