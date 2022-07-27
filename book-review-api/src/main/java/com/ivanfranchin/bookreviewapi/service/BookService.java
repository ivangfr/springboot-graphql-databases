package com.ivanfranchin.bookreviewapi.service;

import com.ivanfranchin.bookreviewapi.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getBooks();

    Book validateAndGetBookById(String id);

    Book saveBook(Book book);

    void deleteBook(Book book);

    Book validateAndGetBookByIsbn(String isbn);
}
