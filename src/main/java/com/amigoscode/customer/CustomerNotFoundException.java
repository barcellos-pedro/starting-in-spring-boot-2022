package com.amigoscode.customer;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(Integer id) {
        super(String.format("Customer not found with ID: %d", id));
    }

}
