package com.mycompany.authorbookapi.repository;

import com.mycompany.authorbookapi.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
