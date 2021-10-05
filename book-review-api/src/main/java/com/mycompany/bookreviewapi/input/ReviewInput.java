package com.mycompany.bookreviewapi.input;

import lombok.Data;

@Data
public class ReviewInput {

    private String reviewer;
    private String comment;
    private Integer rating;
}
