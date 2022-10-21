package com.ssmph.shoppingcart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ssmph.shoppingcart.model.OrderLineItem;

public interface OrderLineItemRepository extends MongoRepository<OrderLineItem, Long> {
    

}
