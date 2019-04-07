package com.mycompany.authorbookapi.repository;

import com.mycompany.authorbookapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
