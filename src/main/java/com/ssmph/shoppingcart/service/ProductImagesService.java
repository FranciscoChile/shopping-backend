package com.ssmph.shoppingcart.service;

import java.util.List;

import com.ssmph.shoppingcart.model.ProductImages;

public interface ProductImagesService {

    ProductImages saveProductImages(ProductImages productImages);

    List<ProductImages> findAllProductImagesBySku(String skuProduct);
    
}