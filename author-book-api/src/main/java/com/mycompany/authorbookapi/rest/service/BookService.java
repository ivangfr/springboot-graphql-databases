package com.mycompany.authorbookapi.rest.service;

import com.mycompany.authorbookapi.model.Book;

public interface BookService {

    Iterable<Book> getAllBooks();

    Book validateAndGetBookById(Long id);

    Book saveBook(Book book);

    void deleteBook(Book book);

}
