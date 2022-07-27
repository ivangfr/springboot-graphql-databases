package com.ivanfranchin.bookreviewapi.model;

import java.time.Instant;

public record Review(String reviewer, String comment, Integer rating, String createdAt) {

    public Review {
        createdAt = Instant.ofEpochSecond(Instant.now().getEpochSecond()).toString();
    }
}
