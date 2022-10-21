package com.ssmph.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductImage {

    @Id
	private String id;

    private String imageProductName;
    private String skuProduct;
    private String idProduct;

}


