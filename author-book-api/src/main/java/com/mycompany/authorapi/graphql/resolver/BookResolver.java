package com.mycompany.authorapi.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.mycompany.authorapi.graphql.service.AuthorService;
import com.mycompany.authorapi.model.Author;
import com.mycompany.authorapi.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookResolver implements GraphQLResolver<Book> {

    private final AuthorService authorService;

    public BookResolver(AuthorService authorService) {
        this.authorService = authorService;
    }

    public Author getAuthor(Book book) {
        return authorService.validateAndGetAuthor(book.getAuthor().getId());
    }

}
