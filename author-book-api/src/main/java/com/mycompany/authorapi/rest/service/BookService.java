package com.mycompany.authorapi.rest.service;

import com.mycompany.authorapi.model.Book;

import java.util.Optional;

public interface BookService {

    Iterable<Book> getBooks();

    Optional<Book> getBook(Long id);

    Book validateAndGetBook(Long id);

    Book saveBook(Book book);

    void deleteBook(Book book);

}
