package com.ivanfranchin.bookreviewapi.graphql.input;

public record ReviewInput(String reviewer, String comment, Integer rating) {
}
