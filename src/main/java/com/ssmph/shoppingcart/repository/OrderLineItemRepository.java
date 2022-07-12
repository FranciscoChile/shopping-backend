package com.ssmph.shoppingcart.repository;

import com.ssmph.shoppingcart.model.OrderLineItem;

import org.springframework.data.repository.CrudRepository;

public interface OrderLineItemRepository extends CrudRepository<OrderLineItem, Long> {
    

}
