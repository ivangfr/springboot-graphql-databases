package com.mycompany.bookreviewapi.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mycompany.bookreviewapi.model.Book;
import com.mycompany.bookreviewapi.service.BookService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    private final BookService bookService;

    public Query(BookService bookService) {
        this.bookService = bookService;
    }

    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    public Book getBook(String id) {
        return bookService.validateAndGetBook(id);
    }
}
