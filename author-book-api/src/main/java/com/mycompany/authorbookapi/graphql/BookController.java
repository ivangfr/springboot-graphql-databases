package com.mycompany.authorbookapi.graphql;

import com.mycompany.authorbookapi.client.BookReviewApiClient;
import com.mycompany.authorbookapi.client.BookReviewApiQueryBuilder;
import com.mycompany.authorbookapi.client.BookReviewApiResult;
import com.mycompany.authorbookapi.graphql.input.BookInput;
import com.mycompany.authorbookapi.graphql.mapper.BookMapper;
import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.model.Book;
import com.mycompany.authorbookapi.model.BookReview;
import com.mycompany.authorbookapi.service.AuthorService;
import com.mycompany.authorbookapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller("GraphQlBookController")
public class BookController {

    private final AuthorService authorService;
    private final BookService bookService;
    private final BookMapper bookMapper;
    private final BookReviewApiQueryBuilder bookReviewApiQueryBuilder;
    private final BookReviewApiClient bookReviewApiClient;

    @QueryMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @QueryMapping
    public Book getBookById(@Argument Long bookId) {
        return bookService.validateAndGetBookById(bookId);
    }

    @MutationMapping
    public Book createBook(@Argument BookInput bookInput) {
        Author author = authorService.validateAndGetAuthorById(bookInput.getAuthorId());
        Book book = bookMapper.toBook(bookInput);
        book.setAuthor(author);
        return bookService.saveBook(book);
    }

    @MutationMapping
    public Book updateBook(@Argument Long bookId, @Argument BookInput bookInput) {
        Book book = bookService.validateAndGetBookById(bookId);
        bookMapper.updateBookFromRequest(bookInput, book);

        Long authorId = bookInput.getAuthorId();
        if (authorId != null) {
            Author author = authorService.validateAndGetAuthorById(authorId);
            book.setAuthor(author);
        }
        return bookService.saveBook(book);
    }

    @MutationMapping
    public Book deleteBook(@Argument Long bookId) {
        Book book = bookService.validateAndGetBookById(bookId);
        bookService.deleteBook(book);
        return book;
    }

    @SchemaMapping(field = "bookReview")
    public BookReview getBookReview(Book book) {
        String graphQLQuery = bookReviewApiQueryBuilder.getBookReviewQuery(book.getIsbn());
        BookReviewApiResult bookReviewApiResult = bookReviewApiClient.getBookReviews(graphQLQuery);
        return new BookReview(bookReviewApiResult);
    }
}
