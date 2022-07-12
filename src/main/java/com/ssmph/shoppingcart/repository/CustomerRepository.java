package com.ssmph.shoppingcart.repository;


import com.ssmph.shoppingcart.model.Customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    
}
