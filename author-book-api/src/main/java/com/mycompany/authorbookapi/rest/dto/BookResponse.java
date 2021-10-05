package com.mycompany.authorbookapi.rest.dto;

import lombok.Value;

@Value
public class BookResponse {

    Long id;
    String isbn;
    String title;
    Integer year;
}
