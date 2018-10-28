package com.mycompany.authorapi.model;

import lombok.Data;

@Data
public class Review {

    private String reviewer;
    private String comment;
    private Integer rating;

}
