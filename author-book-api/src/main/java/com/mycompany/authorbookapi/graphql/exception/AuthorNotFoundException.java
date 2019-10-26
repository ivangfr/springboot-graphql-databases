package com.mycompany.authorbookapi.graphql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthorNotFoundException extends RuntimeException implements GraphQLError {

    private final Map<String, Object> extensions = new HashMap<>();

    public AuthorNotFoundException(String message, Long id) {
        super(message);
        extensions.put("invalidAuthorId", id);
    }

    public AuthorNotFoundException(String message, String name) {
        super(message);
        extensions.put("invalidAuthorName", name);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }
}
