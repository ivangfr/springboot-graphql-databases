package com.mycompany.authorbookapi.repository;

import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(Author author);
}
