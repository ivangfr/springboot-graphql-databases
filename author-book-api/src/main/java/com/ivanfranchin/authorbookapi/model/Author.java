package com.ivanfranchin.authorbookapi.model;

import com.ivanfranchin.authorbookapi.graphql.input.AuthorInput;
import com.ivanfranchin.authorbookapi.restapi.dto.CreateAuthorRequest;
import com.ivanfranchin.authorbookapi.restapi.dto.UpdateAuthorRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@ToString(exclude = "books")
@EqualsAndHashCode(exclude = "books")
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private Set<Book> books = new LinkedHashSet<>();

    @Column(nullable = false)
    private String name;

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

    public static Author from(AuthorInput authorInput) {
        Author author = new Author();
        author.setName(authorInput.name());
        return author;
    }

    public static void updateFrom(AuthorInput authorInput, Author author) {
        if (authorInput.name() != null) {
            author.setName(authorInput.name());
        }
    }

    public static Author from(CreateAuthorRequest createAuthorRequest) {
        Author author = new Author();
        author.setName(createAuthorRequest.name());
        return author;
    }

    public static void updateFrom(UpdateAuthorRequest updateAuthorRequest, Author author) {
        if (updateAuthorRequest.name() != null) {
            author.setName(updateAuthorRequest.name());
        }
    }
}
