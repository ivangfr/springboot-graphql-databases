package com.mycompany.authorapi.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateAuthorDto {

    @ApiModelProperty(example = "Steve")
    private String firstName;

    @ApiModelProperty(example = "Jobs")
    private String lastName;

}
