package com.mycompany.authorbookapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class AuthorBookApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorBookApiApplication.class, args);
    }
}
