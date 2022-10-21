package com.ssmph.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import com.ssmph.shoppingcart.model.Product;

/**
 * ProductService
 */
public interface ProductService {

    Optional<Product> getById(String id);

    Product save(Product product);

    void delete(String id);

    List<Product> list();
    
}