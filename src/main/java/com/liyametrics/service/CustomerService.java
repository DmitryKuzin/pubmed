package com.liyametrics.service;

import com.liyametrics.domain.Customer;
import com.liyametrics.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    public  Iterable<Customer> getCustomers() {
        Iterable<Customer> all = customerRepository.findAll();

        return all;

    }
}
