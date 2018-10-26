package com.mycompany.authorapi.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.mycompany.authorapi.graphql.service.BookService;
import com.mycompany.authorapi.model.Author;
import com.mycompany.authorapi.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorResolver implements GraphQLResolver<Author> {

    private final BookService bookService;

    public AuthorResolver(BookService bookService) {
        this.bookService = bookService;
    }

    public List<Book> getBooks(Author author) {
        return bookService.getBooksByAuthor(author);
    }

}
