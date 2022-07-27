package com.ivanfranchin.authorbookapi.repository;

import com.ivanfranchin.authorbookapi.model.Author;
import com.ivanfranchin.authorbookapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(Author author);
}
