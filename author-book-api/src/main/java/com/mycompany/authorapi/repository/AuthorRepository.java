package com.mycompany.authorapi.repository;

import com.mycompany.authorapi.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
