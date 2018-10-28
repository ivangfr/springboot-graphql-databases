package com.mycompany.authorapi.client;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class BookReviewApiQueryBuilder {

    private static final String BOOK_REVIEW_QUERY = "{ getBookByIsbn(bookIsbn: \"%s\") { reviews {comment, rating, reviewer} } }";

    private final Gson gson;

    public BookReviewApiQueryBuilder(Gson gson) {
        this.gson = gson;
    }

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
