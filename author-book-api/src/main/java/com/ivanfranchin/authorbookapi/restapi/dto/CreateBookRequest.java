package com.ivanfranchin.authorbookapi.restapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateBookRequest(
        @Schema(example = "1") @NotNull Long authorId,
        @Schema(example = "9781617292545") @NotBlank String isbn,
        @Schema(example = "Spring Boot in Action") @NotBlank String title,
        @Schema(example = "2016") @NotNull Integer year) {
}
