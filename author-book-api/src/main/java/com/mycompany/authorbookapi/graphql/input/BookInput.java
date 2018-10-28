package com.mycompany.authorbookapi.graphql.input;

import lombok.Data;

@Data
public class BookInput {

    private String isbn;
    private String title;
    private Integer year;
    private Integer numPages;
    private Long authorId;

}
