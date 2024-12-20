package com.ivanfranchin.bookreviewapi.graphql;

import com.ivanfranchin.bookreviewapi.graphql.input.BookInput;
import com.ivanfranchin.bookreviewapi.graphql.input.ReviewInput;
import com.ivanfranchin.bookreviewapi.model.Book;
import com.ivanfranchin.bookreviewapi.model.Review;
import com.ivanfranchin.bookreviewapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookReviewController {

    private final BookService bookService;

    @QueryMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @QueryMapping
    public Book getBookById(@Argument String bookId) {
        return bookService.validateAndGetBookById(bookId);
    }

    @QueryMapping
    public Book getBookByIsbn(@Argument String bookIsbn) {
        return bookService.validateAndGetBookByIsbn(bookIsbn);
    }

    @MutationMapping
    public Book createBook(@Argument BookInput bookInput) {
        Book book = Book.from(bookInput);
        return bookService.saveBook(book);
    }

    @MutationMapping
    public Book updateBook(@Argument String bookId, @Argument BookInput bookInput) {
        Book book = bookService.validateAndGetBookById(bookId);
        Book.updateFrom(bookInput, book);
        return bookService.saveBook(book);
    }

    @MutationMapping
    public Book deleteBook(@Argument String bookId) {
        Book book = bookService.validateAndGetBookById(bookId);
        bookService.deleteBook(book);
        return book;
    }

    @MutationMapping
    public Book addBookReview(@Argument String bookId, @Argument ReviewInput reviewInput) {
        Book book = bookService.validateAndGetBookById(bookId);
        Review review = Review.from(reviewInput);
        book.getReviews().addFirst(review);
        return bookService.saveBook(book);
    }
}
