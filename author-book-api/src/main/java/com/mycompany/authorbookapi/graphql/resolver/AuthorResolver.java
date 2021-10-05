package com.mycompany.authorbookapi.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.mycompany.authorbookapi.graphql.service.BookService;
import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AuthorResolver implements GraphQLResolver<Author> {

    private final BookService bookService;

    public List<Book> getBooks(Author author) {
        return bookService.getBooksByAuthor(author);
    }
}
