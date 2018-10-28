package com.mycompany.authorbookapi.graphql.service;

import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.model.Book;

import java.util.List;

public interface BookService {

    Iterable<Book> getAllBooks();

    List<Book> getBooksByAuthor(Author author);

    Book validateAndGetBookById(Long id);

    Book saveBook(Book book);

    void deleteBook(Book book);

}
