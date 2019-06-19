package com.mycompany.authorbookapi.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateBookDto {

    @ApiModelProperty(example = "1")
    @NotNull
    private Long authorId;

    @ApiModelProperty(position = 2, example = "9781617292545")
    @NotNull
    @NotEmpty
    private String isbn;

    @ApiModelProperty(position = 3, example = "Spring Boot in Action")
    @NotNull
    @NotEmpty
    private String title;

    @ApiModelProperty(position = 4, example = "2016")
    @NotNull
    private Integer year;

}
