package com.ssmph.shoppingcart.repository;

import java.util.List;

import com.ssmph.shoppingcart.model.ProductImages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * ProductImageRepository
 */
@Repository
public interface ProductImagesRepository extends JpaRepository<ProductImages, Long> {

    @Query (value = "select * from product_images where sku_product = :skuProduct", nativeQuery = true)
    public List<ProductImages> findAllProductImagesBySku(@Param("skuProduct") String skuProduct );



}