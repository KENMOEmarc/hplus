package ken.web.hplus.common.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import ken.web.hplus.customer.CustomerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Map;
import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    static final String CODE_KEY = "code";
    static final String REFERENCE_KEY = "reference";

    @GraphQlExceptionHandler
    public GraphQLError handleNotFound(ResourceNotFoundException ex, DataFetchingEnvironment env) {
        log.debug("Resource not found on '{}': {}", env.getExecutionStepInfo().getPath(), ex.getMessage());

        return GraphqlErrorBuilder.newError(env)
                .errorType(ErrorType.NOT_FOUND)
                .message(ex.getMessage())
                .extensions(Map.of(CODE_KEY, ex.getCode()))
                .build();
    }

    /** Safety net: anything not handled above ends up here. */
    @GraphQlExceptionHandler
    public GraphQLError handleUnexpected(Exception ex, DataFetchingEnvironment env) {
        String reference = UUID.randomUUID().toString();
        log.error("Unexpected error on '{}' [reference={}]", env.getExecutionStepInfo().getPath(), reference, ex);

        return GraphqlErrorBuilder.newError(env)
                .errorType(ErrorType.INTERNAL_ERROR)
                .message("An unexpected error occurred. Please contact support with the given reference.")
                .extensions(Map.of(CODE_KEY, "INTERNAL_ERROR", REFERENCE_KEY, reference))
                .build();
    }
}
