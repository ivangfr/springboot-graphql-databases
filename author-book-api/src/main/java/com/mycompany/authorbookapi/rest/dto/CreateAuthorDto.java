package com.mycompany.authorbookapi.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateAuthorDto {

    @ApiModelProperty(example = "Craig Walls")
    @NotBlank
    private String name;

}
