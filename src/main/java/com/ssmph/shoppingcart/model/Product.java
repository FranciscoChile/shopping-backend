package com.ssmph.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "products")
public class Product {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    private String sku;
	private String nameProduct;
	private Double priceList;
	private Double priceSell;
	private String description;
	private Long stock;
	private String category;
	private int active;
	
	public Product(String sku, String nameProduct, Double priceList, Double priceSell, String description, Long stock,
			String category, int active) {
		this.sku = sku;
		this.nameProduct = nameProduct;
		this.priceList = priceList;
		this.priceSell = priceSell;
		this.description = description;
		this.stock = stock;
		this.category = category;
		this.active = active;
	}

	
	
}