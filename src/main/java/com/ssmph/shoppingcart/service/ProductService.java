package com.ssmph.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import com.ssmph.shoppingcart.model.Product;

/**
 * ProductService
 */
public interface ProductService {

    Optional<Product> getById(Long id);

    Product save(Product product);

    void delete(Long id);

    List<Product> list();
    
}