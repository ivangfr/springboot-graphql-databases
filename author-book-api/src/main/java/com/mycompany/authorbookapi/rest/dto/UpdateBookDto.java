package com.mycompany.authorbookapi.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateBookDto {

    @ApiModelProperty(example = "2")
    private Long authorId;

    @ApiModelProperty(position = 1, example = "9781617291999")
    private String isbn;

    @ApiModelProperty(position = 2, example = "Java 8 in Action")
    private String title;

    @ApiModelProperty(position = 3, example = "2014")
    private Integer year;

}
