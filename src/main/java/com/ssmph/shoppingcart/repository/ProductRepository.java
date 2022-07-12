package com.ssmph.shoppingcart.repository;

import com.ssmph.shoppingcart.model.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
}
