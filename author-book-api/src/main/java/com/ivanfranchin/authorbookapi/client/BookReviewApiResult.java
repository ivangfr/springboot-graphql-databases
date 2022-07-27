package com.ivanfranchin.authorbookapi.client;

import com.ivanfranchin.authorbookapi.model.BookReview;
import com.ivanfranchin.authorbookapi.model.Review;

import java.util.Collections;
import java.util.List;

public record BookReviewApiResult(ResultData data, String error) {

    public record ResultData(QueryName getBookByIsbn) {

        public ResultData() {
            this(null);
        }

        public record QueryName(String id, List<Review> reviews) {
        }
    }

    static BookReviewApiResult empty(String error) {
        return new BookReviewApiResult(new ResultData(), error);
    }

    public BookReview toBookReview() {
        BookReviewApiResult.ResultData.QueryName getBookByIsbn = this.data().getBookByIsbn();
        if (getBookByIsbn == null) {
            String errorStr = this.error() != null ?
                    this.error() : "Unable to get book reviews. Check if there is a book with exact ISBN in book-review-api.";
            return new BookReview(errorStr, null, Collections.emptyList());
        }
        return new BookReview(null, getBookByIsbn.id(), getBookByIsbn.reviews());
    }
}
