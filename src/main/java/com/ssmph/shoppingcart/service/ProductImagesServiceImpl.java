package com.ssmph.shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssmph.shoppingcart.model.ProductImage;
import com.ssmph.shoppingcart.repository.ProductImagesRepository;

/**
 * ProductImagesServiceImpl
 */
@Service
public class ProductImagesServiceImpl implements ProductImagesService {

    @Autowired
    private ProductImagesRepository productImagesRepository;

    @Transactional
    public ProductImage saveProductImages(ProductImage productImages) {
        return productImagesRepository.save(productImages);
    }


    
}