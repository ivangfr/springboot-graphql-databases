package com.mycompany.authorapi.client;

import com.mycompany.authorapi.client.dto.BookReviewApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient("book-review-api")
public interface BookReviewApiClient {

    @PostMapping("/graphql")
    BookReviewApiResult getBookReviews(@Valid @RequestBody String graphQLQuery);

}
