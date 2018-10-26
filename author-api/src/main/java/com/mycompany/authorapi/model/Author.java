package com.mycompany.authorapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@ToString(exclude = "books")
@EqualsAndHashCode(exclude = "books")
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book> books = new LinkedHashSet<>();

    private String firstName;
    private String lastName;

}
