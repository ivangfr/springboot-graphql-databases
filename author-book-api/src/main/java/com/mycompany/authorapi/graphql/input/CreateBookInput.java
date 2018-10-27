package com.mycompany.authorapi.graphql.input;

import lombok.Data;

@Data
public class CreateBookInput {

    private String title;
    private Integer year;
    private Integer numPages;
    private Long authorId;

}
