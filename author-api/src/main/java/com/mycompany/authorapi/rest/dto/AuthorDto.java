package com.mycompany.authorapi.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthorDto {

    private Long id;
    private String firstName;
    private String lastName;
    private List<BookDto> books;

}
