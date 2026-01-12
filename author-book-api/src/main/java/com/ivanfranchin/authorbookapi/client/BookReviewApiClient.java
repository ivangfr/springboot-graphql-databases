package com.ivanfranchin.authorbookapi.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/graphql")
public interface BookReviewApiClient {

    Logger log = LoggerFactory.getLogger(BookReviewApiClient.class);

    @CircuitBreaker(name = "getBookReviews", fallbackMethod = "getBookReviewsFallback")
    @PostExchange(contentType = "application/json")
    BookReviewApiResult getBookReviews(@RequestBody String graphQLQuery);

    default BookReviewApiResult getBookReviewsFallback(String query, Throwable t) {
        String error = String.format("Unable to access book-review-api. Query: %s; Cause: %s", query, t.getMessage());
        log.error(error);
        return BookReviewApiResult.empty(error);
    }
}