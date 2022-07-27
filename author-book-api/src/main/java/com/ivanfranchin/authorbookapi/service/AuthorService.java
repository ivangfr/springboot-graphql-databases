package com.ivanfranchin.authorbookapi.service;

import com.ivanfranchin.authorbookapi.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAuthors();

    Author validateAndGetAuthorById(Long id);

    List<Author> validateAndGetAuthorByName(String name);

    Author saveAuthor(Author author);

    void deleteAuthor(Author author);
}
