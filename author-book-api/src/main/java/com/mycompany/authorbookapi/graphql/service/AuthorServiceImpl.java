package com.mycompany.authorbookapi.graphql.service;

import com.mycompany.authorbookapi.graphql.exception.AuthorNotFoundException;
import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GrapgQLAuthorServiceImpl")
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
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author not found", id));
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
