package com.ivanfranchin.authorbookapi.restapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateBookRequest(
        @Schema(example = "2") Long authorId,
        @Schema(example = "9781617291999") String isbn,
        @Schema(example = "Java 8 in Action") String title,
        @Schema(example = "2014") Integer year) {
}
