package ken.web.hplus.common.exception;

/**
 * Base class for every "resource not found" business error (Customer, Product, Order...).
 * <p>
 * The {@link GlobalExceptionHandler} maps it to a GraphQL {@code NOT_FOUND} error, so each
 * new domain only has to extend this class: no new handler needed.
 */
public abstract class ResourceNotFoundException extends RuntimeException {

    private final String code;

    protected ResourceNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    /** Stable, machine-readable error code exposed to clients (e.g. {@code CUSTOMER_NOT_FOUND}). */
    public String getCode() {
        return code;
    }
}
