package com.mycompany.authorapi.rest.service;

import com.mycompany.authorapi.model.Book;

public interface BookService {

    Iterable<Book> getBooks();

    Book validateAndGetBook(Long id);

    Book saveBook(Book book);

    void deleteBook(Book book);

}
