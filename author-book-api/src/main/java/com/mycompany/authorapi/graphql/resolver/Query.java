package com.mycompany.authorapi.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mycompany.authorapi.graphql.service.AuthorService;
import com.mycompany.authorapi.graphql.service.BookService;
import com.mycompany.authorapi.model.Author;
import com.mycompany.authorapi.model.Book;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    private final AuthorService authorService;
    private final BookService bookService;

    public Query(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    // ------
    // Author

    public Iterable<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    public Author getAuthorById(Long authorId) {
        return authorService.validateAndGetAuthorById(authorId);
    }

    // ----
    // Book

    public Iterable<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public Book getBookById(Long bookId) {
        return bookService.validateAndGetBookById(bookId);
    }

}
