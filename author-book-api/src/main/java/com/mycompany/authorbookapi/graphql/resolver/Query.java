package com.mycompany.authorbookapi.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mycompany.authorbookapi.graphql.service.AuthorService;
import com.mycompany.authorbookapi.graphql.service.BookService;
import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    public Author getAuthorById(Long authorId) {
        return authorService.validateAndGetAuthorById(authorId);
    }

    public Author getAuthorByName(String authorName) {
        return authorService.validateAndGetAuthorByName(authorName);
    }

    // ----
    // Book

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public Book getBookById(Long bookId) {
        return bookService.validateAndGetBookById(bookId);
    }

}
