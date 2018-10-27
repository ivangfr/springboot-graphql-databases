package com.mycompany.bookreviewapi.model;

import lombok.Data;

@Data
public class Review {

    private String reviewer;
    private String comment;
    private Integer rating;

}
