package com.mycompany.authorapi.rest.service;

import com.mycompany.authorapi.model.Author;
import com.mycompany.authorapi.repository.AuthorRepository;
import com.mycompany.authorapi.rest.exception.AuthorNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("RestAuthorServiceImpl")
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Iterable<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> getAuthor(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author validateAndGetAuthor(Long id) {
        return getAuthor(id).orElseThrow(() -> new AuthorNotFoundException(String.format("Author with id '%s' not found", id)));
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Author author) {
        authorRepository.delete(author);
    }
}
