package com.ivanfranchin.authorbookapi.model;

import com.ivanfranchin.authorbookapi.graphql.input.BookInput;
import com.ivanfranchin.authorbookapi.restapi.dto.CreateBookRequest;
import com.ivanfranchin.authorbookapi.restapi.dto.UpdateBookRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.Instant;

@Data
@ToString(exclude = "author")
@EqualsAndHashCode(exclude = "author")
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "FK_AUTHOR"))
    private Author author;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer year;

    @Transient
    private BookReview reviewRes;

    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    public void onPrePersist() {
        createdAt = updatedAt = Instant.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        updatedAt = Instant.now();
    }

    public static Book from(BookInput bookInput) {
        Book book = new Book();
        book.setIsbn(bookInput.isbn());
        book.setTitle(bookInput.title());
        book.setYear(bookInput.year());
        return book;
    }

    public static void updateFrom(BookInput bookInput, Book book) {
        if (bookInput.isbn() != null) {
            book.setIsbn(bookInput.isbn());
        }
        if (bookInput.title() != null) {
            book.setTitle(bookInput.title());
        }
        if (bookInput.year() != null) {
            book.setYear(bookInput.year());
        }
    }

    public static Book from(CreateBookRequest createBookRequest) {
        Book book = new Book();
        book.setIsbn(createBookRequest.isbn());
        book.setTitle(createBookRequest.title());
        book.setYear(createBookRequest.year());
        return book;
    }

    public static void updateFrom(UpdateBookRequest updateBookRequest, Book book) {
        if (updateBookRequest.isbn() != null) {
            book.setIsbn(updateBookRequest.isbn());
        }
        if (updateBookRequest.title() != null) {
            book.setTitle(updateBookRequest.title());
        }
        if (updateBookRequest.year() != null) {
            book.setYear(updateBookRequest.year());
        }
    }
}
