package com.mycompany.bookreviewapi.repository;

import com.mycompany.bookreviewapi.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
