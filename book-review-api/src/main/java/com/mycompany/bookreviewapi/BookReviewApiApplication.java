package com.mycompany.bookreviewapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BookReviewApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookReviewApiApplication.class, args);
    }
}
