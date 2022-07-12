package com.ssmph.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.ssmph.shoppingcart.model.Customer;
import com.ssmph.shoppingcart.repository.CustomerRepository;
import com.ssmph.shoppingcart.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Optional<Customer> getById(Long id) {
        return customerRepository.findById(id);
    }

    @Transactional
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public void delete(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        orderRepository.deleteAll(customer.get().getOrders());
        customerRepository.deleteById(id);
    }

    
    public List<Customer> list() throws DataAccessException {
        return (List<Customer>) customerRepository.findAll();
    }
    
}
