package com.freedom.wishlist.api.v1.controller;

import com.freedom.wishlist.api.v1.dto.CustomerDTO;
import com.freedom.wishlist.api.v1.mapper.Mapper;
import com.freedom.wishlist.domain.Customer;
import com.freedom.wishlist.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("CustomerControllerV1")
@RequestMapping(RestPath.BASE_PATH + "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private Mapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.create(mapper.map(customerDTO, Customer.class)));
    }
}
