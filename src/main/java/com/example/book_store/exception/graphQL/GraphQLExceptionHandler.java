package com.example.book_store.exception.graphQL;

import com.example.book_store.exception.BookAlreadyBorrowedException;
import com.example.book_store.exception.RecordNotFoundException;
import com.example.book_store.exception.EmailIsAlreadyUsedException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.util.HashMap;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if(ex instanceof RecordNotFoundException) {
            return helper(ex.getMessage(),ErrorType.NOT_FOUND,env);
        }else if(ex instanceof EmailIsAlreadyUsedException) {
            return helper(ex.getMessage(),ErrorType.FORBIDDEN,env);
        }else if(ex instanceof BadCredentialsException){
            return helper(ex.getMessage(), ErrorType.BAD_REQUEST,env);
        }else if(ex instanceof BookAlreadyBorrowedException){
            return helper(ex.getMessage(), ErrorType.BAD_REQUEST,env);
        } else if (ex instanceof BindException bindException) {
            HashMap<String,String> errors = new HashMap<>();
            bindException.getBindingResult().getFieldErrors().forEach(error->
                    errors.put(error.getField(),error.getDefaultMessage()));
            return helper(errors.toString(), ErrorType.FORBIDDEN,env);
        }

        return null;
    }
    private GraphQLError helper(String message,ErrorType errorType,DataFetchingEnvironment env){
        return GraphqlErrorBuilder.newError(env)
                .errorType(ErrorType.NOT_FOUND)
                .message(message)
                .build();
    }

}