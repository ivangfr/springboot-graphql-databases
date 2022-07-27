package com.ivanfranchin.authorbookapi.client;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookReviewApiQueryBuilder {

    private static final String BOOK_REVIEW_QUERY =
            "{ getBookByIsbn(bookIsbn: \"%s\") { id, reviews { comment, rating, reviewer, createdAt } } }";

    private final Gson gson;

    public String getBookReviewQuery(String bookIsbn) {
        String query = String.format(BOOK_REVIEW_QUERY, bookIsbn);
        BookReviewApiQuery bookReviewApiQuery = new BookReviewApiQuery(query);
        return gson.toJson(bookReviewApiQuery);
    }

    private record BookReviewApiQuery(String query) {
    }
}
