package com.ivanfranchin.authorbookapi.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class BookReviewApiClientConfig {

    @Value("${book-review-api.url}")
    private String bookReviewApiUrl;

    @Bean
    BookReviewApiClient bookReviewApiClient(RestClient.Builder builder) {
        RestClient restClient = builder.baseUrl(bookReviewApiUrl).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(BookReviewApiClient.class);
    }
}