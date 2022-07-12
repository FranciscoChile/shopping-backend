package com.ssmph.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import com.ssmph.shoppingcart.model.Customer;

/**
 * ProductService
 */
public interface CustomerService {

    Optional<Customer> getById(Long id);

    Customer save(Customer customer);

    void delete(Long id);

    List<Customer> list();
    
}