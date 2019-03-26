package com.mycompany.bookreviewapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class BookReviewApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookReviewApiApplication.class, args);
    }
}
