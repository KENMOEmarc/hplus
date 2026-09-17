package ken.web.hplus.customer;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @QueryMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @QueryMapping
    public Customer getCustomerById(@Argument Long id) {
        return customerService.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @QueryMapping
    public Customer getCustomerByEmail(@Argument String email) {
        return customerService.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(email));
    }
}


