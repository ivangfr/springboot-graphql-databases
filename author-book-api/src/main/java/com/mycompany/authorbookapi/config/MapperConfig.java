package com.mycompany.authorbookapi.config;

import com.mycompany.authorbookapi.graphql.input.AuthorInput;
import com.mycompany.authorbookapi.graphql.input.BookInput;
import com.mycompany.authorbookapi.model.Author;
import com.mycompany.authorbookapi.model.Book;
import com.mycompany.authorbookapi.rest.dto.UpdateAuthorDto;
import com.mycompany.authorbookapi.rest.dto.UpdateBookDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    MapperFactory mapperFactory() {
        DefaultMapperFactory defaultMapperFactory = new DefaultMapperFactory.Builder().useAutoMapping(true).build();

        // ----
        // GraphQL

        defaultMapperFactory.classMap(AuthorInput.class, Author.class).mapNulls(false).byDefault().register();
        defaultMapperFactory.classMap(BookInput.class, Book.class).mapNulls(false).byDefault().register();

        // ----
        // Rest

        defaultMapperFactory.classMap(UpdateAuthorDto.class, Author.class).mapNulls(false).byDefault().register();
        defaultMapperFactory.classMap(UpdateBookDto.class, Book.class).mapNulls(false).byDefault().register();

        return defaultMapperFactory;
    }

    @Bean
    MapperFacade mapperFacade() {
        return mapperFactory().getMapperFacade();
    }
}

