package com.mycompany.authorbookapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateAuthorDto {

    @Schema(example = "Craig Walls")
    @NotBlank
    private String name;

}
