package com.mycompany.authorbookapi.rest.service;

import com.mycompany.authorbookapi.model.Book;
import com.mycompany.authorbookapi.repository.BookRepository;
import com.mycompany.authorbookapi.rest.exception.BookNotFoundException;
import org.springframework.stereotype.Service;

@Service("RestBookServiceImpl")
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book validateAndGetBookById(Long id) {
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
