package ken.web.hplus.controller;

import ken.web.hplus.entities.Customer;
import ken.web.hplus.repositories.CustomerRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerController {
    private final CustomerRepository theCustomerRepository;

    public CustomerController(CustomerRepository theCustomerRepository) {
        this.theCustomerRepository = theCustomerRepository;
    }

    @QueryMapping
    public Iterable<Customer> getAllCustomers() {
        return theCustomerRepository.findAll();
    }

    @QueryMapping
    public Customer getCustomerById(@Argument Long id) {
        return theCustomerRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public Customer getCustomerByEmail(@Argument String email) {
        return theCustomerRepository.findCustomerByEmail(email);
    }


}
