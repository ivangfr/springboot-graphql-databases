package com.mycompany.authorbookapi.rest;

import com.mycompany.authorbookapi.client.BookReviewApiClient;
import com.mycompany.authorbookapi.client.BookReviewApiQueryBuilder;
import com.mycompany.authorbookapi.client.BookReviewApiResult;
import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.model.Book;
import com.mycompany.authorbookapi.model.BookReview;
import com.mycompany.authorbookapi.rest.dto.BookDto;
import com.mycompany.authorbookapi.rest.dto.CreateBookDto;
import com.mycompany.authorbookapi.rest.dto.UpdateBookDto;
import com.mycompany.authorbookapi.rest.service.AuthorService;
import com.mycompany.authorbookapi.rest.service.BookService;
import lombok.extern.slf4j.Slf4j;
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
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final BookReviewApiClient bookReviewApiClient;
    private final BookReviewApiQueryBuilder bookReviewApiQueryBuilder;
    private final MapperFacade mapperFacade;

    public BookController(BookService bookService, AuthorService authorService, BookReviewApiClient bookReviewApiClient,
                          BookReviewApiQueryBuilder bookReviewApiQueryBuilder, MapperFacade mapperFacade) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookReviewApiClient = bookReviewApiClient;
        this.bookReviewApiQueryBuilder = bookReviewApiQueryBuilder;
        this.mapperFacade = mapperFacade;
    }

    @GetMapping
    public List<BookDto> getBooks() {
        return bookService.getAllBooks()
                .stream()
                .map(book -> mapperFacade.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{bookId}")
    public BookDto getBook(@PathVariable Long bookId) {
        Book book = bookService.validateAndGetBookById(bookId);
        return mapperFacade.map(book, BookDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BookDto createBook(@Valid @RequestBody CreateBookDto createBookDto) {
        Author author = authorService.validateAndGetAuthorById(createBookDto.getAuthorId());
        Book book = mapperFacade.map(createBookDto, Book.class);
        book.setAuthor(author);
        book = bookService.saveBook(book);
        return mapperFacade.map(book, BookDto.class);
    }

    @PutMapping("/{bookId}")
    public BookDto updateBook(@PathVariable Long bookId, @Valid @RequestBody UpdateBookDto updateBookDto) {
        Book book = bookService.validateAndGetBookById(bookId);
        mapperFacade.map(updateBookDto, book);
        Long authorId = updateBookDto.getAuthorId();
        if (authorId != null) {
            Author author = authorService.validateAndGetAuthorById(authorId);
            book.setAuthor(author);
        }
        book = bookService.saveBook(book);
        return mapperFacade.map(book, BookDto.class);
    }

    @DeleteMapping("/{bookId}")
    public BookDto deleteBook(@PathVariable Long bookId) {
        Book book = bookService.validateAndGetBookById(bookId);
        bookService.deleteBook(book);
        return mapperFacade.map(book, BookDto.class);
    }

    @GetMapping("/{bookId}/reviews")
    public BookReview getBookReviews(@PathVariable Long bookId) {
        Book book = bookService.validateAndGetBookById(bookId);

        String graphQLQuery = bookReviewApiQueryBuilder.getBookReviewQuery(book.getIsbn());
        BookReviewApiResult bookReviewApiResult = bookReviewApiClient.getBookReviews(graphQLQuery);
        return new BookReview(bookReviewApiResult);
    }

}
