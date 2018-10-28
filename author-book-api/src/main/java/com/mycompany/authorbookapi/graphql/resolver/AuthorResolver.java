package com.mycompany.authorbookapi.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.mycompany.authorbookapi.graphql.service.BookService;
import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.model.Book;
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
