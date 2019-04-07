package com.mycompany.authorbookapi.rest.service;

import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.repository.AuthorRepository;
import com.mycompany.authorbookapi.rest.exception.AuthorNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RestAuthorServiceImpl")
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author validateAndGetAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(String.format("Author with id '%s' not found", id)));
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
