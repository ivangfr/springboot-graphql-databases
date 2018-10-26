package com.mycompany.authorapi.rest.dto;

import lombok.Data;

@Data
public class BookDto {

    private Long id;
    private String title;
    private Integer year;

}
