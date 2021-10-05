package com.mycompany.bookreviewapi.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.mycompany.bookreviewapi.input.BookInput;
import com.mycompany.bookreviewapi.input.ReviewInput;
import com.mycompany.bookreviewapi.mapper.BookMapper;
import com.mycompany.bookreviewapi.mapper.ReviewMapper;
import com.mycompany.bookreviewapi.model.Book;
import com.mycompany.bookreviewapi.model.Review;
import com.mycompany.bookreviewapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Mutation implements GraphQLMutationResolver {

    private final BookService bookService;
    private final BookMapper bookMapper;
    private final ReviewMapper reviewMapper;

    public Book createBook(BookInput bookInput) {
        Book book = bookMapper.toBook(bookInput);
        return bookService.saveBook(book);
    }

    public Book updateBook(String bookId, BookInput bookInput) {
        Book book = bookService.validateAndGetBookById(bookId);
        bookMapper.updateBookFromInput(bookInput, book);
        return bookService.saveBook(book);
    }

    public Book deleteBook(String bookId) {
        Book book = bookService.validateAndGetBookById(bookId);
        bookService.deleteBook(book);
        return book;
    }

    public Book addBookReview(String bookId, ReviewInput reviewInput) {
        Book book = bookService.validateAndGetBookById(bookId);
        Review review = reviewMapper.toReview(reviewInput);
        book.getReviews().add(0, review);
        return bookService.saveBook(book);
    }
}
