package com.ivanfranchin.bookreviewapi.model;

import lombok.Data;

import java.time.Instant;

@Data
public class Review {

    private String reviewer;
    private String comment;
    private Integer rating;
    private String createdAt;

    public Review() {
        createdAt = Instant.ofEpochSecond(Instant.now().getEpochSecond()).toString();
    }
}
