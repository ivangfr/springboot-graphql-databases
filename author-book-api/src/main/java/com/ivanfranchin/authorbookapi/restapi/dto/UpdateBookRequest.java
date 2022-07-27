package com.ivanfranchin.authorbookapi.restapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateBookRequest {

    @Schema(example = "2")
    private Long authorId;

    @Schema(example = "9781617291999")
    private String isbn;

    @Schema(example = "Java 8 in Action")
    private String title;

    @Schema(example = "2014")
    private Integer year;
}
