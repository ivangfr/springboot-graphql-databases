package com.ivanfranchin.authorbookapi.repository;

import com.ivanfranchin.authorbookapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByNameContainingOrderByName(String authorName);
}
