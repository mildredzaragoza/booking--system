package com.aspire.userservice.exception;

import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;

/**
 * Class to implement Exception resolution.
 * @author Mildred Zaragoza
 *
 */
@Component
public class GraphQLExceptionResolver extends DataFetcherExceptionResolverAdapter{

	@Override
	protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
		if(ex instanceof UserNotFoundException) {
			return GraphqlErrorBuilder.newError()
					.errorType(ErrorType.NOT_FOUND)
					.message(ex.getMessage())
					.build();
		}
		return null;
	}

}
