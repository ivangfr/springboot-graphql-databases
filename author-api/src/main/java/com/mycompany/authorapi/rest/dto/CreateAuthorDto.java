package com.mycompany.authorapi.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateAuthorDto {

    @ApiModelProperty(example = "Ivan")
    @NotNull
    @NotEmpty
    private String firstName;

    @ApiModelProperty(position = 2, example = "Franchin")
    @NotNull
    @NotEmpty
    private String lastName;

}
