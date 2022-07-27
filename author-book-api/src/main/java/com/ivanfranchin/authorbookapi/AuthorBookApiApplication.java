package com.ivanfranchin.authorbookapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AuthorBookApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorBookApiApplication.class, args);
    }
}
