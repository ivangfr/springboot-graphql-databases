package com.mycompany.authorbookapi.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateAuthorDto {

    @ApiModelProperty(example = "Craig Walls")
    @NotNull
    @NotEmpty
    private String name;

}
