package com.mycompany.authorapi.rest;

import com.mycompany.authorapi.graphql.service.AuthorService;
import com.mycompany.authorapi.graphql.service.BookService;
import com.mycompany.authorapi.model.Author;
import com.mycompany.authorapi.model.Book;
import com.mycompany.authorapi.rest.dto.BookDto;
import com.mycompany.authorapi.rest.dto.CreateBookDto;
import com.mycompany.authorapi.rest.dto.UpdateBookDto;
import com.mycompany.authorapi.rest.exception.AuthorNotFoundException;
import com.mycompany.authorapi.rest.exception.BookNotFoundException;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final MapperFacade mapperFacade;

    public BookController(BookService bookService, AuthorService authorService, MapperFacade mapperFacade) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.mapperFacade = mapperFacade;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Iterable<Book> getBooks() {
        return bookService.getBooks();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{bookId}")
    public BookDto getBook(@PathVariable Long bookId) {
        Book book = bookService.validateAndGetBook(bookId);
        return mapperFacade.map(book, BookDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BookDto createBook(@Valid @RequestBody CreateBookDto createBookDto) {
        Author author = authorService.validateAndGetAuthor(createBookDto.getAuthorId());
        Book book = mapperFacade.map(createBookDto, Book.class);
        book.setAuthor(author);
        book = bookService.saveBook(book);
        return mapperFacade.map(book, BookDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{bookId}")
    public BookDto updateBook(@PathVariable Long bookId, @Valid @RequestBody UpdateBookDto updateBookDto) {
        Book book = bookService.validateAndGetBook(bookId);
        mapperFacade.map(updateBookDto, book);

        Long authorId = updateBookDto.getAuthorId();
        if (authorId != null) {
            Author author = authorService.validateAndGetAuthor(authorId);
            book.setAuthor(author);
        }

        book = bookService.saveBook(book);
        return mapperFacade.map(book, BookDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping("/{bookId}")
    public BookDto deleteBook(@PathVariable Long bookId) {
        Book book = bookService.validateAndGetBook(bookId);
        bookService.deleteBook(book);
        return mapperFacade.map(book, BookDto.class);
    }

}
