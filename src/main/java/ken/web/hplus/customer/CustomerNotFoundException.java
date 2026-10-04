package ken.web.hplus.customer;

import ken.web.hplus.common.exception.ResourceNotFoundException;

public class CustomerNotFoundException extends ResourceNotFoundException {
    private static final String CODE = "CUSTOMER_NOT_FOUND";

    private CustomerNotFoundException(String message) {
        super(CODE, message);
    }

    public static CustomerNotFoundException byId(Long id) {
        return new CustomerNotFoundException("Customer not found with id: " + id);
    }

    public static CustomerNotFoundException byEmail(String email) {
        return new CustomerNotFoundException("Customer not found with email: " + email);
    }
}