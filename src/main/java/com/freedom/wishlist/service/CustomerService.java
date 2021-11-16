package com.freedom.wishlist.service;

import com.freedom.wishlist.domain.Customer;
import com.freedom.wishlist.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public Customer create(Customer customer) {
        return customerRepository.insert(customer);
    }

    @Transactional(readOnly = true)
    public Boolean customerIsPresent(String customerId) {
        return customerRepository.existsById(customerId);
    }
}
