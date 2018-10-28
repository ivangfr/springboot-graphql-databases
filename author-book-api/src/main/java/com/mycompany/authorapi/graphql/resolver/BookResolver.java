package com.mycompany.authorapi.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.mycompany.authorapi.client.BookReviewApiClient;
import com.mycompany.authorapi.client.BookReviewApiQueryBuilder;
import com.mycompany.authorapi.client.dto.BookReviewApiResult;
import com.mycompany.authorapi.graphql.service.AuthorService;
import com.mycompany.authorapi.model.Author;
import com.mycompany.authorapi.model.Book;
import com.mycompany.authorapi.model.Review;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class BookResolver implements GraphQLResolver<Book> {

    private final AuthorService authorService;
    private final BookReviewApiClient bookReviewApiClient;
    private final BookReviewApiQueryBuilder bookReviewApiQueryBuilder;

    public BookResolver(AuthorService authorService, BookReviewApiClient bookReviewApiClient,
                        BookReviewApiQueryBuilder bookReviewApiQueryBuilder) {
        this.authorService = authorService;
        this.bookReviewApiClient = bookReviewApiClient;
        this.bookReviewApiQueryBuilder = bookReviewApiQueryBuilder;
    }

    public Author getAuthor(Book book) {
        return authorService.validateAndGetAuthorById(book.getAuthor().getId());
    }

    public List<Review> getReviews(Book book) {
        String graphQLQuery = bookReviewApiQueryBuilder.getBookReviewQuery(book.getIsbn());
        BookReviewApiResult result = bookReviewApiClient.getBookReviews(graphQLQuery);

        BookReviewApiResult.ResultData.QueryName getBookByIsbn = result.getData().getGetBookByIsbn();
        if (getBookByIsbn == null) {
            log.warn("Book with isbn '{}' not found in book-review-api", book.getIsbn());
            return Collections.emptyList();
        } else {
            return getBookByIsbn.getReviews();
        }
    }

}
