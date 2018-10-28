package com.mycompany.bookreviewapi.service;

import com.mycompany.bookreviewapi.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book validateAndGetBookById(String id);

    Book saveBook(Book book);

    void deleteBook(Book book);

    Book validateAndGetBookByIsbn(String isbn);
}
