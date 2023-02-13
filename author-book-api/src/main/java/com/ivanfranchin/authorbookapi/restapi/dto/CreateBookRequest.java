package com.ivanfranchin.authorbookapi.restapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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
