package com.mycompany.authorapi.client.dto;

import com.mycompany.authorapi.model.Review;
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

}
