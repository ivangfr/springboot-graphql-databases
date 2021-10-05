package com.mycompany.authorbookapi.rest.service;

import com.mycompany.authorbookapi.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAllAuthors();

    Author validateAndGetAuthorById(Long id);

    Author validateAndGetAuthorByName(String name);

    Author saveAuthor(Author author);

    void deleteAuthor(Author author);
}
