package com.mycompany.authorapi.rest.service;

import com.mycompany.authorapi.model.Book;

public interface BookService {

    Iterable<Book> getAllBooks();

    Book validateAndGetBookById(Long id);

    Book saveBook(Book book);

    void deleteBook(Book book);

}
