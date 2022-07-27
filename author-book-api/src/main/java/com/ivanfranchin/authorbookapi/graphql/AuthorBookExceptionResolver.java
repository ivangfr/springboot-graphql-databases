package com.ivanfranchin.authorbookapi.graphql;

import com.ivanfranchin.authorbookapi.exception.AuthorNotFoundException;
import com.ivanfranchin.authorbookapi.exception.BookNotFoundException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class AuthorBookExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        ErrorType errorType = ErrorType.INTERNAL_ERROR;
        if (ex instanceof BookNotFoundException || ex instanceof AuthorNotFoundException) {
            errorType = ErrorType.NOT_FOUND;
        }

        return GraphqlErrorBuilder.newError(env)
                .message("Resolved error: " + ex.getMessage())
                .errorType(errorType)
                .build();
    }
}
