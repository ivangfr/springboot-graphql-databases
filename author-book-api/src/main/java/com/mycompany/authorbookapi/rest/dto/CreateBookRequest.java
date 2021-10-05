package com.mycompany.authorbookapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateBookRequest {

    @Schema(example = "1")
    @NotNull
    private Long authorId;

    @Schema(example = "9781617292545")
    @NotBlank
    private String isbn;

    @Schema(example = "Spring Boot in Action")
    @NotBlank
    private String title;

    @Schema(example = "2016")
    @NotNull
    private Integer year;
}
