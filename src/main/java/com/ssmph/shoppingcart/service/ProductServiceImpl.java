package com.ssmph.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import com.ssmph.shoppingcart.model.Product;
import com.ssmph.shoppingcart.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> list() throws DataAccessException {
        return (List<Product>) productRepository.findAll();
    }

    public Optional<Product> getById(String id) {
        return productRepository.findById(id) ;
    }

    @Transactional
    public void delete(String id) {
        productRepository.deleteById(id);
    }
    
}