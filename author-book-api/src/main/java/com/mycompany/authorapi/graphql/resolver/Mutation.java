package com.mycompany.authorapi.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.mycompany.authorapi.graphql.input.AuthorInput;
import com.mycompany.authorapi.graphql.input.BookInput;
import com.mycompany.authorapi.graphql.service.AuthorService;
import com.mycompany.authorapi.graphql.service.BookService;
import com.mycompany.authorapi.model.Author;
import com.mycompany.authorapi.model.Book;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    private final AuthorService authorService;
    private final BookService bookService;
    private final MapperFacade mapperFacade;

    public Mutation(AuthorService authorService, BookService bookService, MapperFacade mapperFacade) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.mapperFacade = mapperFacade;
    }

    // ------
    // Author

    public Author createAuthor(AuthorInput authorInput) {
        Author author = mapperFacade.map(authorInput, Author.class);
        return authorService.saveAuthor(author);
    }

    public Author updateAuthor(Long authorId, AuthorInput authorInput) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        mapperFacade.map(authorInput, author);
        return authorService.saveAuthor(author);
    }

    public Author deleteAuthor(Long authorId) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        authorService.deleteAuthor(author);
        return author;
    }

    // ----
    // Book

    public Book createBook(BookInput bookInput) {
        Author author = authorService.validateAndGetAuthorById(bookInput.getAuthorId());
        Book book = mapperFacade.map(bookInput, Book.class);
        book.setAuthor(author);
        return bookService.saveBook(book);
    }

    public Book updateBook(Long bookId, BookInput bookInput) {
        Book book = bookService.validateAndGetBookById(bookId);
        mapperFacade.map(bookInput, book);

        Long authorId = bookInput.getAuthorId();
        if (authorId != null) {
            Author author = authorService.validateAndGetAuthorById(authorId);
            book.setAuthor(author);
        }

        return bookService.saveBook(book);
    }

    public Book deleteBook(Long bookId) {
        Book book = bookService.validateAndGetBookById(bookId);
        bookService.deleteBook(book);
        return book;
    }

}
