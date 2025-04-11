package org.example.repository;

import org.example.entity.Customer;

public class CustomerRepository extends FoundationReposirory<Customer>  {
    public CustomerRepository() {
        super(Customer.class);
    }
}
