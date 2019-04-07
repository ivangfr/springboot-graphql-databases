package com.mycompany.authorbookapi.rest.dto;

import lombok.Data;

@Data
public class BookDto {

    private Long id;
    private String isbn;
    private String title;
    private Integer year;
    private Integer numPages;

}
