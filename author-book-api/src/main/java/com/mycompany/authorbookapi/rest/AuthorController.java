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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final MapperFacade mapperFacade;

    public AuthorController(AuthorService authorService, MapperFacade mapperFacade) {
        this.authorService = authorService;
        this.mapperFacade = mapperFacade;
    }

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors()
                .stream()
                .map(author -> mapperFacade.map(author, AuthorDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/name/{authorName}")
    public AuthorDto getAuthorByName(@PathVariable String authorName) {
        Author author = authorService.validateAndGetAuthorByName(authorName);
        return mapperFacade.map(author, AuthorDto.class);
    }

    @GetMapping("/{authorId}")
    public AuthorDto getAuthorById(@PathVariable Long authorId) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        return mapperFacade.map(author, AuthorDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AuthorDto createAuthor(@Valid @RequestBody CreateAuthorDto createAuthorDto) {
        Author author = mapperFacade.map(createAuthorDto, Author.class);
        author = authorService.saveAuthor(author);
        return mapperFacade.map(author, AuthorDto.class);
    }

    @PutMapping("/{authorId}")
    public AuthorDto updateAuthor(@PathVariable Long authorId, @Valid @RequestBody UpdateAuthorDto updateAuthorDto) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        mapperFacade.map(updateAuthorDto, author);
        author = authorService.saveAuthor(author);
        return mapperFacade.map(author, AuthorDto.class);
    }

    @DeleteMapping("/{authorId}")
    public AuthorDto deleteAuthor(@PathVariable Long authorId) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        authorService.deleteAuthor(author);
        return mapperFacade.map(author, AuthorDto.class);
    }

    @GetMapping("/{authorId}/books")
    public Set<BookDto> getAuthorBooks(@PathVariable Long authorId) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        return author.getBooks()
                .stream()
                .map(book -> mapperFacade.map(book, BookDto.class))
                .collect(Collectors.toSet());
    }

}
