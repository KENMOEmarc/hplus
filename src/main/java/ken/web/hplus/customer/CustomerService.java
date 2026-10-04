package ken.web.hplus.customer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> CustomerNotFoundException.byId(id));
    }

    public Customer findByEmail(String email) {
        return customerRepository.findByEmailIgnoreCase(email).orElseThrow(
                () -> CustomerNotFoundException.byEmail("Customer not found with email: " + email));
    }
}