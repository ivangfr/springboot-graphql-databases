package com.ivanfranchin.authorbookapi.restapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAuthorRequest {

    @Schema(example = "Craig Walls")
    @NotBlank
    private String name;
}
