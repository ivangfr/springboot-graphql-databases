package com.ivanfranchin.authorbookapi.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "book-review-api",
        url = "http://${BOOK_REVIEW_API_HOST:localhost}:${BOOK_REVIEW_API_PORT:9080}",
        fallbackFactory = BookReviewApiClient.BookReviewApiClientFallbackFactory.class)
public interface BookReviewApiClient {

    @PostMapping(path = "/graphql", consumes = "application/json")
    BookReviewApiResult getBookReviews(@RequestBody String graphQLQuery);

    // ----------------
    // Fallback Factory

    @Component
    class BookReviewApiClientFallbackFactory implements FallbackFactory<BookReviewApiClient> {

        @Override
        public BookReviewApiClient create(Throwable throwable) {
            return new BookReviewApiClientFallback(throwable);
        }
    }

    // --------------
    // Client Fallback

    @Slf4j
    @RequiredArgsConstructor
    class BookReviewApiClientFallback implements BookReviewApiClient {

        private final Throwable cause;

        @Override
        public BookReviewApiResult getBookReviews(String graphQLQuery) {
            String error = String.format("Unable to access book-review-api. Cause: %s", cause.getMessage());
            log.error(error);
            return BookReviewApiResult.empty(error);
        }
    }
}
