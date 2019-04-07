package com.mycompany.authorbookapi.repository;

import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(Author author);

}
