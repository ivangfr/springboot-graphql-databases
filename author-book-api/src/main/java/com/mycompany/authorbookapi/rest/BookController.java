package com.mycompany.authorbookapi.rest;

import com.mycompany.authorbookapi.client.BookReviewApiClient;
import com.mycompany.authorbookapi.client.BookReviewApiQueryBuilder;
import com.mycompany.authorbookapi.client.dto.BookReviewApiResult;
import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.model.Book;
import com.mycompany.authorbookapi.model.Review;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Iterable<BookDto> getAllBooks() {
        return StreamSupport.stream(bookService.getAllBooks().spliterator(), false)
                .map(book -> mapperFacade.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{bookId}")
    public BookDto getBookById(@PathVariable Long bookId) {
        Book book = bookService.validateAndGetBookById(bookId);
        return mapperFacade.map(book, BookDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long createBook(@Valid @RequestBody CreateBookDto createBookDto) {
        Author author = authorService.validateAndGetAuthorById(createBookDto.getAuthorId());
        Book book = mapperFacade.map(createBookDto, Book.class);
        book.setAuthor(author);
        return bookService.saveBook(book).getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{bookId}")
    public Long updateBook(@PathVariable Long bookId, @Valid @RequestBody UpdateBookDto updateBookDto) {
        Book book = bookService.validateAndGetBookById(bookId);
        mapperFacade.map(updateBookDto, book);
        Long authorId = updateBookDto.getAuthorId();
        if (authorId != null) {
            Author author = authorService.validateAndGetAuthorById(authorId);
            book.setAuthor(author);
        }
        return bookService.saveBook(book).getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{bookId}")
    public Long deleteBook(@PathVariable Long bookId) {
        Book book = bookService.validateAndGetBookById(bookId);
        bookService.deleteBook(book);
        return book.getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{bookId}/reviews")
    public List<Review> getBookReviews(@PathVariable Long bookId) {
        Book book = bookService.validateAndGetBookById(bookId);

        String graphQLQuery = bookReviewApiQueryBuilder.getBookReviewQuery(book.getIsbn());
        BookReviewApiResult result = bookReviewApiClient.getBookReviews(graphQLQuery);

        BookReviewApiResult.ResultData.QueryName getBookByIsbn = result.getData().getGetBookByIsbn();
        if (getBookByIsbn == null) {
            log.warn("Book with isbn '{}' not found in book-review-api", book.getIsbn());
            return Collections.emptyList();
        } else {
            return getBookByIsbn.getReviews();
        }
    }

}
