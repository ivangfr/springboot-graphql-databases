package com.mycompany.bookreviewapi.graphql.input;

import lombok.Data;

@Data
public class BookInput {

    private String isbn;
    private String title;
}