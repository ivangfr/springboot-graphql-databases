package com.mycompany.authorapi.repository;

import com.mycompany.authorapi.model.Author;
import com.mycompany.authorapi.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByAuthor(Author author);

}
