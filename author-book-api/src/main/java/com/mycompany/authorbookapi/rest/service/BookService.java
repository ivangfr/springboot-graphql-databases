package com.mycompany.authorbookapi.rest.service;

import com.mycompany.authorbookapi.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book validateAndGetBookById(Long id);

    Book saveBook(Book book);

    void deleteBook(Book book);

}
