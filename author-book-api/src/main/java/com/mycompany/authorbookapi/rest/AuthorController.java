package com.mycompany.authorbookapi.rest;

import com.mycompany.authorbookapi.mapper.AuthorMapper;
import com.mycompany.authorbookapi.mapper.BookMapper;
import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.rest.dto.AuthorDto;
import com.mycompany.authorbookapi.rest.dto.BookDto;
import com.mycompany.authorbookapi.rest.dto.CreateAuthorDto;
import com.mycompany.authorbookapi.rest.dto.UpdateAuthorDto;
import com.mycompany.authorbookapi.rest.service.AuthorService;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors()
                .stream()
                .map(authorMapper::toAuthorDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/name/{authorName}")
    public AuthorDto getAuthorByName(@PathVariable String authorName) {
        Author author = authorService.validateAndGetAuthorByName(authorName);
        return authorMapper.toAuthorDto(author);
    }

    @GetMapping("/{authorId}")
    public AuthorDto getAuthorById(@PathVariable Long authorId) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        return authorMapper.toAuthorDto(author);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AuthorDto createAuthor(@Valid @RequestBody CreateAuthorDto createAuthorDto) {
        Author author = authorMapper.toAuthor(createAuthorDto);
        author = authorService.saveAuthor(author);
        return authorMapper.toAuthorDto(author);
    }

    @PutMapping("/{authorId}")
    public AuthorDto updateAuthor(@PathVariable Long authorId, @Valid @RequestBody UpdateAuthorDto updateAuthorDto) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        authorMapper.updateAuthorFromDto(updateAuthorDto, author);
        author = authorService.saveAuthor(author);
        return authorMapper.toAuthorDto(author);
    }

    @DeleteMapping("/{authorId}")
    public AuthorDto deleteAuthor(@PathVariable Long authorId) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        authorService.deleteAuthor(author);
        return authorMapper.toAuthorDto(author);
    }

    @GetMapping("/{authorId}/books")
    public Set<BookDto> getAuthorBooks(@PathVariable Long authorId) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        return author.getBooks()
                .stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toSet());
    }

}
