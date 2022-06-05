package com.mycompany.authorbookapi.service;

import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getBooks();

    List<Book> getBooksByAuthor(Author author);

    Book validateAndGetBookById(Long id);

    Book saveBook(Book book);

    void deleteBook(Book book);
}
