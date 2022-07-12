package com.ssmph.shoppingcart.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "customers")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Customer {
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String country;
    private Boolean active;

    @OneToMany(
        mappedBy = "customer",
        orphanRemoval = true
    )
    List<Order> orders = new ArrayList<>();

    public Customer(String name, String email, String phone, String address, String city, String country, Boolean active) {
     this.name = name;
     this.email = email;
     this.phone = phone;
     this.address = address;
     this.city = city;
     this.country = country;
     this.active = active;
    }

}
