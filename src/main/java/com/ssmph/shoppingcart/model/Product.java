package com.ssmph.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Product
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
	private String id;

    private String sku;
	private String name;
	private Double priceList;
	private Double priceSell;
	private String description;
	private Long stock;
	private String category;
	private int active;
	private String profileImg;
	
	public Product(String sku, String name, Double priceList, Double priceSell, String description, Long stock,
			String category, int active) {
		this.sku = sku;
		this.name = name;
		this.priceList = priceList;
		this.priceSell = priceSell;
		this.description = description;
		this.stock = stock;
		this.category = category;
		this.active = active;
	}

	
	
}