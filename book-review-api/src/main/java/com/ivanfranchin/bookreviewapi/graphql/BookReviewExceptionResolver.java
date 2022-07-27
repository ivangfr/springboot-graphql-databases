package com.ivanfranchin.bookreviewapi.graphql;

import com.ivanfranchin.bookreviewapi.exception.BookDuplicatedIsbnException;
import com.ivanfranchin.bookreviewapi.exception.BookNotFoundException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class BookReviewExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        ErrorType errorType = ErrorType.INTERNAL_ERROR;
        if (ex instanceof BookNotFoundException) {
            errorType = ErrorType.NOT_FOUND;
        } else if (ex instanceof BookDuplicatedIsbnException) {
            errorType = ErrorType.BAD_REQUEST;
        }

        return GraphqlErrorBuilder.newError(env)
                .message("Resolved error: " + ex.getMessage())
                .errorType(errorType)
                .build();
    }
}
