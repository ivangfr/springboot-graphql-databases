package com.mycompany.authorbookapi.repository;

import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByAuthor(Author author);

}
