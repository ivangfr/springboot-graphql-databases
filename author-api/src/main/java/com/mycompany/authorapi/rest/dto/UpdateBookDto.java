package com.mycompany.authorapi.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateBookDto {

    @ApiModelProperty(example = "2")
    private Long authorId;

    @ApiModelProperty(position = 2, example = "Introduction to Java9")
    private String title;

    @ApiModelProperty(position = 3, example = "2019")
    private Integer year;

    @ApiModelProperty(position = 4, example = "356")
    private Integer numPages;

}
