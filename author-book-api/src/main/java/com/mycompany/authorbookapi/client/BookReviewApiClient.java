package com.mycompany.authorbookapi.client;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "book-review-api", fallbackFactory = BookReviewApiClient.BookReviewApiClientFallbackFactory.class)
public interface BookReviewApiClient {

    @PostMapping("/graphql")
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
    class BookReviewApiClientFallback implements BookReviewApiClient {

        private final Throwable cause;

        BookReviewApiClientFallback(Throwable cause) {
            this.cause = cause;
        }

        @Override
        public BookReviewApiResult getBookReviews(String graphQLQuery) {
            log.error("Unable to access book-review-api. Cause: {}", cause.getMessage());
            return BookReviewApiResult.empty();
        }
    }

}
