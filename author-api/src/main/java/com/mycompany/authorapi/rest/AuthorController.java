package com.mycompany.authorapi.rest;

import com.mycompany.authorapi.model.Author;
import com.mycompany.authorapi.repository.AuthorRepository;
import com.mycompany.authorapi.rest.dto.AuthorDto;
import com.mycompany.authorapi.rest.dto.CreateAuthorDto;
import com.mycompany.authorapi.rest.dto.UpdateAuthorDto;
import com.mycompany.authorapi.rest.exception.AuthorNotFoundException;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final MapperFacade mapperFacade;

    public AuthorController(AuthorRepository authorRepository, MapperFacade mapperFacade) {
        this.authorRepository = authorRepository;
        this.mapperFacade = mapperFacade;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Iterable<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public AuthorDto getAuthor(@PathVariable Long id) {
        Author author = validateAndGetAuthor(id);
        return mapperFacade.map(author, AuthorDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AuthorDto createAuthor(@Valid @RequestBody CreateAuthorDto createAuthorDto) {
        Author author = mapperFacade.map(createAuthorDto, Author.class);
        author = authorRepository.save(author);
        return mapperFacade.map(author, AuthorDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public AuthorDto updateAuthor(@PathVariable Long id, @Valid @RequestBody UpdateAuthorDto updateAuthorDto) {
        Author author = validateAndGetAuthor(id);
        mapperFacade.map(updateAuthorDto, author);
        author = authorRepository.save(author);
        return mapperFacade.map(author, AuthorDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public AuthorDto deleteAuthor(@PathVariable Long id) {
        Author author = validateAndGetAuthor(id);
        authorRepository.delete(author);
        return mapperFacade.map(author, AuthorDto.class);
    }

    private Author validateAndGetAuthor(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(String.format("Author with id '%s' not found", id)));
    }

}
