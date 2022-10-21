package com.ssmph.shoppingcart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ssmph.shoppingcart.model.ProductImage;

/**
 * ProductImageRepository
 */
@Repository
public interface ProductImagesRepository extends MongoRepository<ProductImage, Long> {


}