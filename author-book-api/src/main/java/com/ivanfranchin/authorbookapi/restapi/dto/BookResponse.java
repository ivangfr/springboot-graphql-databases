package com.ivanfranchin.authorbookapi.restapi.dto;

import com.ivanfranchin.authorbookapi.model.Book;

public record BookResponse(Long id, String isbn, String title, Integer year) {

    public static BookResponse from(Book book) {
        return new BookResponse(book.getId(), book.getIsbn(), book.getTitle(), book.getYear());
    }
}
