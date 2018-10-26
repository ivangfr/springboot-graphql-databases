package com.mycompany.authorapi.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.mycompany.authorapi.graphql.input.CreateAuthorInput;
import com.mycompany.authorapi.graphql.input.CreateBookInput;
import com.mycompany.authorapi.graphql.input.UpdateAuthorInput;
import com.mycompany.authorapi.graphql.input.UpdateBookInput;
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

    public Author createAuthor(CreateAuthorInput createAuthorInput) {
        Author author = mapperFacade.map(createAuthorInput, Author.class);
        return authorService.saveAuthor(author);
    }

    public Author updateAuthor(Long id, UpdateAuthorInput updateAuthorInput) {
        Author author = authorService.validateAndGetAuthor(id);
        mapperFacade.map(updateAuthorInput, author);
        return authorService.saveAuthor(author);
    }

    public Author deleteAuthor(Long id) {
        Author author = authorService.validateAndGetAuthor(id);
        authorService.deleteAuthor(author);
        return author;
    }

    // ----
    // Book

    public Book createBook(CreateBookInput createBookInput) {
        Author author = authorService.validateAndGetAuthor(createBookInput.getAuthorId());
        Book book = mapperFacade.map(createBookInput, Book.class);
        book.setAuthor(author);
        return bookService.saveBook(book);
    }

    public Book updateBook(Long id, UpdateBookInput updateBookInput) {
        Book book = bookService.validateAndGetBook(id);
        mapperFacade.map(updateBookInput, book);

        Long authorId = updateBookInput.getAuthorId();
        if (authorId != null) {
            Author author = authorService.validateAndGetAuthor(authorId);
            book.setAuthor(author);
        }

        return bookService.saveBook(book);
    }

    public Book deleteBook(Long id) {
        Book book = bookService.validateAndGetBook(id);
        bookService.deleteBook(book);
        return book;
    }

}
