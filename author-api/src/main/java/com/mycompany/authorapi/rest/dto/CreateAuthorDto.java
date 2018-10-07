package com.mycompany.authorapi.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateAuthorDto {

    @ApiModelProperty(example = "Ivan")
    @NotNull
    @NotEmpty
    private String firstName;

    @ApiModelProperty(example = "Franchin")
    @NotNull
    @NotEmpty
    private String lastName;

}
