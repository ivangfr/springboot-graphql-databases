package com.ivanfranchin.authorbookapi.service;

import com.ivanfranchin.authorbookapi.model.Author;
import com.ivanfranchin.authorbookapi.repository.BookRepository;
import com.ivanfranchin.authorbookapi.exception.BookNotFoundException;
import com.ivanfranchin.authorbookapi.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        return bookRepository.findByAuthor(author);
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
