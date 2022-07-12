package com.ssmph.shoppingcart.repository;



import com.ssmph.shoppingcart.model.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    
}
