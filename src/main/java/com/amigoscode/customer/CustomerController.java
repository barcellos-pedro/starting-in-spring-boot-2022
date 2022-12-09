package com.amigoscode.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// combines @Controller and @ResponseBody. All class methods will return HTTP/JSON response
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.findAll();
    }

    @GetMapping("{id}")
    public Customer getCustomer(@PathVariable("id") int id) throws CustomerNotFoundException {
        return customerService.getCustomer(id);
    }

    @PostMapping
    public Customer addCustomer(@RequestBody CustomerRequest request) {
        return customerService.addCustomer(request);
    }

    @PutMapping("{id}")
    public Customer updateCustomer(@PathVariable("id") int id, @RequestBody CustomerRequest request) throws CustomerNotFoundException {
        return customerService.updateCustomer(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
    }
}
