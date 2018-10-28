package com.mycompany.authorbookapi.model;

import lombok.Data;

@Data
public class Review {

    private String reviewer;
    private String comment;
    private Integer rating;

}
