package com.ivanfranchin.authorbookapi.model;

public record Review(String reviewer, String comment, Integer rating, String createdAt) {
}
