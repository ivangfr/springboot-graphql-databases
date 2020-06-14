package com.mycompany.authorbookapi.client;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
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

    @Data
    @AllArgsConstructor
    private static class BookReviewApiQuery {
        private String query;
    }
}
