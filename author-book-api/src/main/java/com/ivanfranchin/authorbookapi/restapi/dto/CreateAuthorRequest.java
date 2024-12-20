package com.ivanfranchin.authorbookapi.restapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateAuthorRequest(@Schema(example = "Craig Walls") @NotBlank String name) {
}
