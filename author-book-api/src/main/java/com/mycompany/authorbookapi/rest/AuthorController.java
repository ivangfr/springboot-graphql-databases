package com.mycompany.authorbookapi.rest;

import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.rest.dto.AuthorDto;
import com.mycompany.authorbookapi.rest.dto.BookDto;
import com.mycompany.authorbookapi.rest.dto.CreateAuthorDto;
import com.mycompany.authorbookapi.rest.dto.UpdateAuthorDto;
import com.mycompany.authorbookapi.rest.service.AuthorService;
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
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final MapperFacade mapperFacade;

    public AuthorController(AuthorService authorService, MapperFacade mapperFacade) {
        this.authorService = authorService;
        this.mapperFacade = mapperFacade;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Iterable<AuthorDto> getAllAuthors() {
        return StreamSupport.stream(authorService.getAllAuthors().spliterator(), false)
                .map(author -> mapperFacade.map(author, AuthorDto.class))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{authorId}")
    public AuthorDto getAuthorById(@PathVariable Long authorId) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        return mapperFacade.map(author, AuthorDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long createAuthor(@Valid @RequestBody CreateAuthorDto createAuthorDto) {
        Author author = mapperFacade.map(createAuthorDto, Author.class);
        return authorService.saveAuthor(author).getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{authorId}")
    public Long updateAuthor(@PathVariable Long authorId, @Valid @RequestBody UpdateAuthorDto updateAuthorDto) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        mapperFacade.map(updateAuthorDto, author);
        return authorService.saveAuthor(author).getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{authorId}")
    public Long deleteAuthor(@PathVariable Long authorId) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        authorService.deleteAuthor(author);
        return author.getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{authorId}/books")
    public Set<BookDto> getAuthorBooks(@PathVariable Long authorId) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        return author.getBooks()
                .stream()
                .map(book -> mapperFacade.map(book, BookDto.class))
                .collect(Collectors.toSet());
    }

}
