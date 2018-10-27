package com.mycompany.authorapi.graphql.service;

import com.mycompany.authorapi.graphql.exception.BookNotFoundException;
import com.mycompany.authorapi.model.Author;
import com.mycompany.authorapi.model.Book;
import com.mycompany.authorapi.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("GraphQLBookServiceImpl")
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
    public List<Book> getBooksByAuthor(Author author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public Optional<Book> getBook(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book validateAndGetBook(Long id) {
        return getBook(id).orElseThrow(() -> new BookNotFoundException("Book not found", id));
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
