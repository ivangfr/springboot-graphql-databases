package com.mycompany.authorapi.graphql.service;

import com.mycompany.authorapi.model.Author;
import com.mycompany.authorapi.model.Book;

import java.util.List;

public interface BookService {

    Iterable<Book> getBooks();

    List<Book> getBooksByAuthor(Author author);

    Book validateAndGetBook(Long id);

    Book saveBook(Book book);

    void deleteBook(Book book);

}
