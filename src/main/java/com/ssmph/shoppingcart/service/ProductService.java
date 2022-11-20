package com.ssmph.shoppingcart.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.ssmph.shoppingcart.model.Product;

/**
 * ProductService
 */
public interface ProductService {

    Optional<Product> getById(String id);

    Product save(Product product, MultipartFile file) throws IOException;

    Product save(Product product);

    void delete(String id);

    List<Product> list();
    
}