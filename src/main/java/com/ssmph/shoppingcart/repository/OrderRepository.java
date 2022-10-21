package com.ssmph.shoppingcart.repository;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.ssmph.shoppingcart.model.Order;

public interface OrderRepository extends MongoRepository<Order, Long> {
    
}
