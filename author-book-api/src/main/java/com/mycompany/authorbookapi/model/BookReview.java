package com.mycompany.authorbookapi.model;

import com.mycompany.authorbookapi.client.BookReviewApiResult;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class BookReview {

    private String error;
    private String id;
    private List<Review> reviews;

    public BookReview(BookReviewApiResult bookReviewApiResult) {
        BookReviewApiResult.ResultData.QueryName getBookByIsbn = bookReviewApiResult.getData().getGetBookByIsbn();
        if (getBookByIsbn == null) {
            String errorStr = bookReviewApiResult.getError();
            if (errorStr == null) {
                errorStr = "Unable to get book reviews. Check if there is a book with exact ISBN in book-review-api.";
            }
            this.error = errorStr;
            this.reviews = Collections.emptyList();
        } else {
            this.id = getBookByIsbn.getId();
            this.reviews = getBookByIsbn.getReviews();
        }
    }
}
