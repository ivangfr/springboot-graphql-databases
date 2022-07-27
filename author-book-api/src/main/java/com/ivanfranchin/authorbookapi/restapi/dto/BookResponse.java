package com.ivanfranchin.authorbookapi.restapi.dto;

import lombok.Value;

@Value
public class BookResponse {

    Long id;
    String isbn;
    String title;
    Integer year;
}
