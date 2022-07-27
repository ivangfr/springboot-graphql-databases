package com.ivanfranchin.bookreviewapi.exception;

public class BookDuplicatedIsbnException extends RuntimeException {

    public BookDuplicatedIsbnException(String message) {
        super(message);
    }
}
