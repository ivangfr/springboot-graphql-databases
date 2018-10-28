package com.mycompany.authorbookapi.rest.service;

import com.mycompany.authorbookapi.model.Author;

public interface AuthorService {

    Iterable<Author> getAllAuthors();

    Author validateAndGetAuthorById(Long id);

    Author saveAuthor(Author author);

    void deleteAuthor(Author author);

}
