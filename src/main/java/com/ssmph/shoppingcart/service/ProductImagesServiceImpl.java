package com.ssmph.shoppingcart.service;

import java.util.List;

import com.ssmph.shoppingcart.model.ProductImages;
import com.ssmph.shoppingcart.repository.ProductImagesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProductImagesServiceImpl
 */
@Service
public class ProductImagesServiceImpl implements ProductImagesService {

    @Autowired
    private ProductImagesRepository productImagesRepository;

    @Transactional
    public ProductImages saveProductImages(ProductImages productImages) {
        return productImagesRepository.save(productImages);
    }

    @Transactional
    public List<ProductImages> findAllProductImagesBySku(String skuProduct) throws DataAccessException {
        return productImagesRepository.findAllProductImagesBySku(skuProduct);
    }
    
}