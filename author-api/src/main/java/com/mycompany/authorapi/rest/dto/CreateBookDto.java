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

    @ApiModelProperty(position = 2, example = "Introduction to GraphQL")
    @NotNull
    @NotEmpty
    private String title;

    @ApiModelProperty(position = 3, example = "2018")
    @NotNull
    private Integer year;

}
