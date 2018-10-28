package com.mycompany.bookreviewapi.repository;

import com.mycompany.bookreviewapi.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findByIsbn(String isbn);

}
