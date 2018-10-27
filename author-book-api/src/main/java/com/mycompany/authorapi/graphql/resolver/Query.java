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

    public Iterable<Author> getAuthors() {
        return authorService.getAuthors();
    }

    public Author getAuthor(Long authorId) {
        return authorService.validateAndGetAuthor(authorId);
    }

    // ----
    // Book

    public Iterable<Book> getBooks() {
        return bookService.getBooks();
    }

    public Book getBook(Long bookId) {
        return bookService.validateAndGetBook(bookId);
    }

}
