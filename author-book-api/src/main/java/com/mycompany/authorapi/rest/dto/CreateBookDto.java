package com.mycompany.authorapi.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateBookDto {

    @ApiModelProperty(example = "1")
    @NotNull
    private Long authorId;

    @ApiModelProperty(position = 2, example = "123")
    @NotNull
    @NotEmpty
    private String isbn;

    @ApiModelProperty(position = 3, example = "Introduction to GraphQL")
    @NotNull
    @NotEmpty
    private String title;

    @ApiModelProperty(position = 4, example = "2018")
    @NotNull
    private Integer year;

    @ApiModelProperty(position = 5, example = "512")
    @NotNull
    private Integer numPages;

}
