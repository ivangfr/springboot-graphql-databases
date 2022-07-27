package com.ivanfranchin.bookreviewapi.graphql.input;

import lombok.Data;

@Data
public class ReviewInput {

    private String reviewer;
    private String comment;
    private Integer rating;
}
