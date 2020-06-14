package com.mycompany.authorbookapi.rest;

import com.mycompany.authorbookapi.client.BookReviewApiClient;
import com.mycompany.authorbookapi.client.BookReviewApiQueryBuilder;
import com.mycompany.authorbookapi.client.BookReviewApiResult;
import com.mycompany.authorbookapi.mapper.BookMapper;
import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.model.Book;
import com.mycompany.authorbookapi.model.BookReview;
import com.mycompany.authorbookapi.rest.dto.BookDto;
import com.mycompany.authorbookapi.rest.dto.CreateBookDto;
import com.mycompany.authorbookapi.rest.dto.UpdateBookDto;
import com.mycompany.authorbookapi.rest.service.AuthorService;
import com.mycompany.authorbookapi.rest.service.BookService;
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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final BookReviewApiClient bookReviewApiClient;
    private final BookReviewApiQueryBuilder bookReviewApiQueryBuilder;
    private final BookMapper bookMapper;

    @GetMapping
    public List<BookDto> getBooks() {
        return bookService.getAllBooks()
                .stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{bookId}")
    public BookDto getBook(@PathVariable Long bookId) {
        Book book = bookService.validateAndGetBookById(bookId);
        return bookMapper.toBookDto(book);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BookDto createBook(@Valid @RequestBody CreateBookDto createBookDto) {
        Author author = authorService.validateAndGetAuthorById(createBookDto.getAuthorId());
        Book book = bookMapper.toBook(createBookDto);
        book.setAuthor(author);
        book = bookService.saveBook(book);
        return bookMapper.toBookDto(book);
    }

    @PutMapping("/{bookId}")
    public BookDto updateBook(@PathVariable Long bookId, @Valid @RequestBody UpdateBookDto updateBookDto) {
        Book book = bookService.validateAndGetBookById(bookId);
        bookMapper.updateBookFromDto(updateBookDto, book);
        Long authorId = updateBookDto.getAuthorId();
        if (authorId != null) {
            Author author = authorService.validateAndGetAuthorById(authorId);
            book.setAuthor(author);
        }
        book = bookService.saveBook(book);
        return bookMapper.toBookDto(book);
    }

    @DeleteMapping("/{bookId}")
    public BookDto deleteBook(@PathVariable Long bookId) {
        Book book = bookService.validateAndGetBookById(bookId);
        bookService.deleteBook(book);
        return bookMapper.toBookDto(book);
    }

    @GetMapping("/{bookId}/reviews")
    public BookReview getBookReviews(@PathVariable Long bookId) {
        Book book = bookService.validateAndGetBookById(bookId);

        String graphQLQuery = bookReviewApiQueryBuilder.getBookReviewQuery(book.getIsbn());
        BookReviewApiResult bookReviewApiResult = bookReviewApiClient.getBookReviews(graphQLQuery);
        return new BookReview(bookReviewApiResult);
    }

}
