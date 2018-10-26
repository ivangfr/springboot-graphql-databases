package com.mycompany.authorapi.config;

import com.mycompany.authorapi.graphql.input.UpdateAuthorInput;
import com.mycompany.authorapi.graphql.input.UpdateBookInput;
import com.mycompany.authorapi.model.Author;
import com.mycompany.authorapi.model.Book;
import com.mycompany.authorapi.rest.dto.UpdateAuthorDto;
import com.mycompany.authorapi.rest.dto.UpdateBookDto;
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

        defaultMapperFactory.classMap(UpdateAuthorInput.class, Author.class).mapNulls(false).byDefault().register();
        defaultMapperFactory.classMap(UpdateBookInput.class, Book.class).mapNulls(false).byDefault().register();

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

