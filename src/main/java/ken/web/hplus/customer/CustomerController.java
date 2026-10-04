package ken.web.hplus.customer;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

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
        return customerService.findById(id);
    }

    @QueryMapping
    public Customer getCustomerByEmail(@Argument String email) {
        return customerService.findByEmail(email);
    }
}


