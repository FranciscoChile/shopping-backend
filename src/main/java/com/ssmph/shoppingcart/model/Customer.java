package com.ssmph.shoppingcart.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Customer {
    
    @Id
	private String id;

    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String country;
    private Boolean active;
    private String profileImg;

    @OneToMany(
        mappedBy = "customer",
        orphanRemoval = true
    )
    List<Order> orders = new ArrayList<>();

    public Customer(
        String name, String email, String phone, 
        String address, String city, String country, Boolean active
    ) {
     this.name = name;
     this.email = email;
     this.phone = phone;
     this.address = address;
     this.city = city;
     this.country = country;
     this.active = active;
    }

}
