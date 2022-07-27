package com.ivanfranchin.authorbookapi.client;

import com.ivanfranchin.authorbookapi.model.Review;
import lombok.Data;

import java.util.List;

@Data
public class BookReviewApiResult {

    private ResultData data;
    private String error;

    @Data
    public static class ResultData {
        private QueryName getBookByIsbn;

        @Data
        public static class QueryName {
            private String id;
            private List<Review> reviews;
        }
    }

    static BookReviewApiResult empty(String error) {
        BookReviewApiResult bookReviewApiResult = new BookReviewApiResult();
        bookReviewApiResult.setError(error);
        bookReviewApiResult.setData(new ResultData());
        return bookReviewApiResult;
    }
}
