package com.mycompany.authorbookapi.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateBookDto {

    @ApiModelProperty(example = "1")
    @NotNull
    private Long authorId;

    @ApiModelProperty(position = 1, example = "9781617292545")
    @NotBlank
    private String isbn;

    @ApiModelProperty(position = 2, example = "Spring Boot in Action")
    @NotBlank
    private String title;

    @ApiModelProperty(position = 3, example = "2016")
    @NotNull
    private Integer year;

}
