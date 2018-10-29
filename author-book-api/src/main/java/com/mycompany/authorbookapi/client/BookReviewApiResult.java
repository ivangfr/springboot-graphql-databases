package com.mycompany.authorbookapi.client;

import com.mycompany.authorbookapi.model.Review;
import lombok.Data;

import java.util.List;

@Data
public class BookReviewApiResult {

    private ResultData data;

    @Data
    public static class ResultData {
        private QueryName getBookByIsbn;

        @Data
        public static class QueryName {
            private List<Review> reviews;
        }
    }

    static BookReviewApiResult empty() {
        BookReviewApiResult bookReviewApiResult = new BookReviewApiResult();
        bookReviewApiResult.setData(new ResultData());
        return bookReviewApiResult;
    }

}
