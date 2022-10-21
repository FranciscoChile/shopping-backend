package com.ssmph.shoppingcart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ssmph.shoppingcart.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    
}
