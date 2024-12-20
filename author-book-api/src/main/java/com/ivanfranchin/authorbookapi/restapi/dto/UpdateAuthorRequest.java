package com.ivanfranchin.authorbookapi.restapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateAuthorRequest(@Schema(example = "Ivan Franchin") String name) {
}
