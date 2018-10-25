package com.mycompany.authorapi.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.mycompany.authorapi.model.Author;
import com.mycompany.authorapi.repository.AuthorRepository;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    private final AuthorRepository authorRepository;

    public Mutation(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author createAuthor(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorRepository.save(author);
        return author;
    }

}
