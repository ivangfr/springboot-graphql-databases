package com.ivanfranchin.bookreviewapi.model;

import com.ivanfranchin.bookreviewapi.graphql.input.BookInput;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    @Indexed(unique = true)
    private String isbn;

    private String title;

    private List<Review> reviews = new ArrayList<>();

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    public static Book from(BookInput bookInput) {
        Book book = new Book();
        book.setIsbn(bookInput.isbn());
        book.setTitle(bookInput.title());
        return book;
    }

    public static void updateFrom(BookInput bookInput, Book book) {
        if (bookInput.isbn() != null) {
            book.setIsbn(bookInput.isbn());
        }

        if (bookInput.title() != null) {
            book.setTitle(bookInput.title());
        }
    }
}
