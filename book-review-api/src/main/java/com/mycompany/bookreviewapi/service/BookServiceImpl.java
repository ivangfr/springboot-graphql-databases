package com.mycompany.bookreviewapi.service;

import com.mycompany.bookreviewapi.exception.BookNotFoundException;
import com.mycompany.bookreviewapi.model.Book;
import com.mycompany.bookreviewapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book validateAndGetBookById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found", "id", id));
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public Book validateAndGetBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException("Book not found", "isbn", isbn));
    }
}
