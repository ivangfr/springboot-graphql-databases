package com.mycompany.authorbookapi.rest.service;

import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.repository.AuthorRepository;
import com.mycompany.authorbookapi.rest.exception.AuthorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("RestAuthorServiceImpl")
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author validateAndGetAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(String.format("Author with id '%s' not found", id)));
    }

    @Override
    public Author validateAndGetAuthorByName(String name) {
        final String nameNormSpace = StringUtils.normalizeSpace(name);
        return authorRepository.findByNameIgnoreCase(nameNormSpace)
                .stream()
                .findFirst()
                .orElseThrow(() -> new AuthorNotFoundException(String.format("Author with name '%s' not found", nameNormSpace)));
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
