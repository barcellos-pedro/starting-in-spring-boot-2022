package com.amigoscode.customer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(int id) throws CustomerNotFoundException {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public Customer addCustomer(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setAge(request.getAge());
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(int id, CustomerRequest request) throws CustomerNotFoundException {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException(id);
        }

        var customerToUpdate = customerOptional.get();
        customerToUpdate.setName(request.getName());
        customerToUpdate.setEmail(request.getEmail());
        customerToUpdate.setAge(request.getAge());

        return customerRepository.save(customerToUpdate);
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
