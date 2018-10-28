package com.mycompany.authorapi.rest.service;

import com.mycompany.authorapi.model.Book;
import com.mycompany.authorapi.repository.BookRepository;
import com.mycompany.authorapi.rest.exception.BookNotFoundException;
import org.springframework.stereotype.Service;

@Service("RestBookServiceImpl")
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book validateAndGetBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(String.format("Book with id '%s' not found", id)));
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }
}
