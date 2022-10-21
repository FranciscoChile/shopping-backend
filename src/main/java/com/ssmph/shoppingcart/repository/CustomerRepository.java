package com.ssmph.shoppingcart.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.ssmph.shoppingcart.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    
}
