package com.ivanfranchin.authorbookapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookDuplicatedIsbnException extends RuntimeException {

    public BookDuplicatedIsbnException(String message) {
        super(message);
    }
}
