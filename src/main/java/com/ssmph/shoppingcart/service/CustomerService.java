package com.ssmph.shoppingcart.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.ssmph.shoppingcart.model.Customer;

/**
 * ProductService
 */
public interface CustomerService {

    Optional<Customer> getById(String id);

    Customer save(Customer customer, MultipartFile file) throws IOException;

    Customer save(Customer customer);

    void delete(String id);

    List<Customer> list();
    
}