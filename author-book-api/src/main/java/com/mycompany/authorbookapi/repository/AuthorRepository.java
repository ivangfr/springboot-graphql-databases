package com.mycompany.authorbookapi.repository;

import com.mycompany.authorbookapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByNameIgnoreCase(String authorName);

}
