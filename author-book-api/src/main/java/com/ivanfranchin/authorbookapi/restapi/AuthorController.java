package com.ivanfranchin.authorbookapi.restapi;

import com.ivanfranchin.authorbookapi.model.Author;
import com.ivanfranchin.authorbookapi.restapi.dto.AuthorResponse;
import com.ivanfranchin.authorbookapi.restapi.dto.BookResponse;
import com.ivanfranchin.authorbookapi.restapi.dto.CreateAuthorRequest;
import com.ivanfranchin.authorbookapi.restapi.dto.UpdateAuthorRequest;
import com.ivanfranchin.authorbookapi.restapi.mapper.AuthorMapper;
import com.ivanfranchin.authorbookapi.restapi.mapper.BookMapper;
import com.ivanfranchin.authorbookapi.service.AuthorService;
import jakarta.validation.Valid;
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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController("RestApiAuthorController")
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    @GetMapping
    public List<AuthorResponse> getAuthors() {
        return authorService.getAuthors()
                .stream()
                .map(authorMapper::toAuthorResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/name/{authorName}")
    public List<AuthorResponse> getAuthorByName(@PathVariable String authorName) {
        return authorService.validateAndGetAuthorByName(authorName)
                .stream()
                .map(authorMapper::toAuthorResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{authorId}")
    public AuthorResponse getAuthorById(@PathVariable Long authorId) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        return authorMapper.toAuthorResponse(author);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AuthorResponse createAuthor(@Valid @RequestBody CreateAuthorRequest createAuthorRequest) {
        Author author = authorMapper.toAuthor(createAuthorRequest);
        author = authorService.saveAuthor(author);
        return authorMapper.toAuthorResponse(author);
    }

    @PutMapping("/{authorId}")
    public AuthorResponse updateAuthor(@PathVariable Long authorId, @Valid @RequestBody UpdateAuthorRequest updateAuthorRequest) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        authorMapper.updateAuthorFromRequest(updateAuthorRequest, author);
        author = authorService.saveAuthor(author);
        return authorMapper.toAuthorResponse(author);
    }

    @DeleteMapping("/{authorId}")
    public AuthorResponse deleteAuthor(@PathVariable Long authorId) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        authorService.deleteAuthor(author);
        return authorMapper.toAuthorResponse(author);
    }

    @GetMapping("/{authorId}/books")
    public Set<BookResponse> getAuthorBooks(@PathVariable Long authorId) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        return author.getBooks()
                .stream()
                .map(bookMapper::toBookResponse)
                .collect(Collectors.toSet());
    }
}
