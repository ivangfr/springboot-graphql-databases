package com.mycompany.bookreviewapi.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.mycompany.bookreviewapi.input.BookInput;
import com.mycompany.bookreviewapi.input.ReviewInput;
import com.mycompany.bookreviewapi.model.Book;
import com.mycompany.bookreviewapi.model.Review;
import com.mycompany.bookreviewapi.service.BookService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    private final BookService bookService;
    private final MapperFacade mapperFacade;

    public Mutation(BookService bookService, MapperFacade mapperFacade) {
        this.bookService = bookService;
        this.mapperFacade = mapperFacade;
    }

    public Book createBook(BookInput bookInput) {
        Book book = mapperFacade.map(bookInput, Book.class);
        return bookService.saveBook(book);
    }

    public Book updateBook(String bookId, BookInput bookInput) {
        Book book = bookService.validateAndGetBookById(bookId);
        mapperFacade.map(bookInput, book);
        return bookService.saveBook(book);
    }

    public Book deleteBook(String bookId) {
        Book book = bookService.validateAndGetBookById(bookId);
        bookService.deleteBook(book);
        return book;
    }

    public Book addBookReview(String bookId, ReviewInput reviewInput) {
        Book book = bookService.validateAndGetBookById(bookId);
        Review review = mapperFacade.map(reviewInput, Review.class);
        book.getReviews().add(0, review);
        return bookService.saveBook(book);
    }
}
