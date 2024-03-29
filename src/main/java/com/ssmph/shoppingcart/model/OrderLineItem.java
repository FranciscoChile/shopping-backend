package com.ssmph.shoppingcart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class OrderLineItem {
    
    @Id
	private String id;

    @Column(name = "product_id")
    String productId;

    @ManyToOne(fetch = FetchType.LAZY)
    Order order;

    Double price;
    Double quantity;

    
    public OrderLineItem(Double price, Double quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    

}
