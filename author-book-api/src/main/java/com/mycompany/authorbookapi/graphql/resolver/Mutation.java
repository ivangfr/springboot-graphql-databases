package com.mycompany.authorbookapi.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.mycompany.authorbookapi.graphql.input.AuthorInput;
import com.mycompany.authorbookapi.graphql.input.BookInput;
import com.mycompany.authorbookapi.graphql.service.AuthorService;
import com.mycompany.authorbookapi.graphql.service.BookService;
import com.mycompany.authorbookapi.mapper.AuthorMapper;
import com.mycompany.authorbookapi.mapper.BookMapper;
import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Mutation implements GraphQLMutationResolver {

    private final AuthorService authorService;
    private final BookService bookService;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    // ------
    // Author

    public Author createAuthor(AuthorInput authorInput) {
        Author author = authorMapper.toAuthor(authorInput);
        return authorService.saveAuthor(author);
    }

    public Author updateAuthor(Long authorId, AuthorInput authorInput) {
        Author author = authorService.validateAndGetAuthorById(authorId);
        authorMapper.updateAuthorFromRequest(authorInput, author);
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
        Book book = bookMapper.toBook(bookInput);
        book.setAuthor(author);
        return bookService.saveBook(book);
    }

    public Book updateBook(Long bookId, BookInput bookInput) {
        Book book = bookService.validateAndGetBookById(bookId);
        bookMapper.updateBookFromRequest(bookInput, book);

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
