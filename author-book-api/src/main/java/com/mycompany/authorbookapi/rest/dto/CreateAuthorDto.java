package com.mycompany.authorbookapi.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    @ApiModelProperty(position = 3, example = "2001-01-01")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;

}
