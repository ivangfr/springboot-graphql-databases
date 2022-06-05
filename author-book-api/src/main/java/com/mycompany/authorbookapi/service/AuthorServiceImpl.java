package com.mycompany.authorbookapi.service;

import com.mycompany.authorbookapi.exception.AuthorNotFoundException;
import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author validateAndGetAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(String.format("Author with id '%s' not found", id)));
    }

    @Override
    public List<Author> validateAndGetAuthorByName(String name) {
        return authorRepository.findByNameContainingOrderByName(StringUtils.normalizeSpace(name))
                .stream()
                .collect(Collectors.toList());
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