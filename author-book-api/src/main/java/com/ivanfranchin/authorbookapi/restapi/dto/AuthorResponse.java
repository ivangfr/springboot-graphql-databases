package com.ivanfranchin.authorbookapi.restapi.dto;

import com.ivanfranchin.authorbookapi.model.Author;

public record AuthorResponse(Long id, String name) {

    public static AuthorResponse from(Author author) {
        return new AuthorResponse(author.getId(), author.getName());
    }
}
