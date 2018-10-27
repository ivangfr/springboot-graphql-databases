package com.mycompany.bookreviewapi.service;

import com.mycompany.bookreviewapi.exception.BookNotFoundException;
import com.mycompany.bookreviewapi.model.Book;
import com.mycompany.bookreviewapi.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBook(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book validateAndGetBook(String id) {
        return getBook(id).orElseThrow(() -> new BookNotFoundException(String.format("Book with id '%s' not found", id)));
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
