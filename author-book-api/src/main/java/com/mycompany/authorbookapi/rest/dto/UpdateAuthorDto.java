package com.mycompany.authorbookapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateAuthorDto {

    @Schema(example = "Ivan Franchin")
    private String name;

}
