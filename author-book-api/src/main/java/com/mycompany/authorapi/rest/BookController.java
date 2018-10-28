package com.mycompany.authorapi.rest;

import com.mycompany.authorapi.model.Author;
import com.mycompany.authorapi.model.Book;
import com.mycompany.authorapi.rest.dto.CreateBookDto;
import com.mycompany.authorapi.rest.dto.UpdateBookDto;
import com.mycompany.authorapi.rest.service.AuthorService;
import com.mycompany.authorapi.rest.service.BookService;
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
    public Iterable<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable Long bookId) {
        return bookService.validateAndGetBookById(bookId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Book createBook(@Valid @RequestBody CreateBookDto createBookDto) {
        Author author = authorService.validateAndGetAuthorById(createBookDto.getAuthorId());
        Book book = mapperFacade.map(createBookDto, Book.class);
        book.setAuthor(author);
        return bookService.saveBook(book);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable Long bookId, @Valid @RequestBody UpdateBookDto updateBookDto) {
        Book book = bookService.validateAndGetBookById(bookId);
        mapperFacade.map(updateBookDto, book);
        Long authorId = updateBookDto.getAuthorId();
        if (authorId != null) {
            Author author = authorService.validateAndGetAuthorById(authorId);
            book.setAuthor(author);
        }
        return bookService.saveBook(book);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping("/{bookId}")
    public Book deleteBook(@PathVariable Long bookId) {
        Book book = bookService.validateAndGetBookById(bookId);
        bookService.deleteBook(book);
        return book;
    }

}
