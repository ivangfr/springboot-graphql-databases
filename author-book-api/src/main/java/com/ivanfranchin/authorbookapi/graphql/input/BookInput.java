package com.ivanfranchin.authorbookapi.graphql.input;

import lombok.Data;

@Data
public class BookInput {

    private String isbn;
    private String title;
    private Integer year;
    private Long authorId;
}
