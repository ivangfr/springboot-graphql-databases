package com.mycompany.authorbookapi.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateAuthorDto {

    @ApiModelProperty(example = "Ivan Franchin")
    private String name;

}
