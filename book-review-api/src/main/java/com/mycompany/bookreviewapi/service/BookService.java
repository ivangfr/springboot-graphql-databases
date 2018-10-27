package com.mycompany.bookreviewapi.service;

import com.mycompany.bookreviewapi.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getBooks();

    Optional<Book> getBook(String id);

    Book validateAndGetBook(String id);

    Book saveBook(Book book);

    void deleteBook(Book book);

}
