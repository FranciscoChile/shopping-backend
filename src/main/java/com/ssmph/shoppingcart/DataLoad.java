package com.ssmph.shoppingcart;

import java.time.LocalDate;

import com.ssmph.shoppingcart.model.Customer;
import com.ssmph.shoppingcart.model.Order;
import com.ssmph.shoppingcart.model.OrderLineItem;
import com.ssmph.shoppingcart.model.Product;
import com.ssmph.shoppingcart.repository.CustomerRepository;
import com.ssmph.shoppingcart.repository.OrderLineItemRepository;
import com.ssmph.shoppingcart.repository.OrderRepository;
import com.ssmph.shoppingcart.repository.ProductRepository;

import org.springframework.boot.CommandLineRunner;



//@Component
public class DataLoad implements CommandLineRunner {

private OrderRepository orderRepository;
private ProductRepository productRepository;
private CustomerRepository customerRepository;
private OrderLineItemRepository orderLineItemRepository;


    public DataLoad(
        OrderRepository orderRepository, ProductRepository productRepository, 
        CustomerRepository customerRepository, OrderLineItemRepository orderLineItemRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderLineItemRepository = orderLineItemRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        Product p1 = new Product("SKU1", "pan", 20D,null, "", null, null, 0);
        productRepository.save(p1);        
        Product p2 = new Product("SKU2", "pan", 20D,null, "", null, null, 0);
        productRepository.save(p2);

        Customer c1 = new Customer("Juan Perez", "sdasd@asdasd.com", "12345678", "mi casa 2", "chile", "Valpo", true);
        customerRepository.save(c1);

        Order order1 = new Order(LocalDate.now(), "");

        OrderLineItem ol1 = new OrderLineItem(1d, 1d);
        ol1.setOrder(order1);
        ol1.setProductId(p1.getId());
        order1.getLineItems().add(ol1);
        

        OrderLineItem ol2 = new OrderLineItem(2d, 2d);
        ol2.setOrder(order1);
        ol2.setProductId(p2.getId());
        order1.getLineItems().add(ol2);
        
        //order1.getCustomer().setId(c1.getId());
        orderRepository.save(order1);

        orderLineItemRepository.save(ol1);
        orderLineItemRepository.save(ol2);

        Product p3 = new Product("SKU3", "pan", 20D,null, "", null, null, 0);
        productRepository.save(p3);        

        Customer c2 = new Customer("Juan Lopez", "sdasd@asdasd.com", "12345678", "mi casa 2", "Valpo", "Chile", true);
        customerRepository.save(c2);

        Order order2 = new Order(LocalDate.now(), "");

        OrderLineItem ol3 = new OrderLineItem(1d, 1d);
        ol3.setOrder(order2);
        ol3.setProductId(p3.getId());
        order2.getLineItems().add(ol3);

        //order2.getCustomer().setId(c2.getId());
        orderRepository.save(order2);

        orderLineItemRepository.save(ol3);

        System.out.println("Number of productos : " + productRepository.count());
        System.out.println("Number of ordenes : " + orderRepository.count());
        System.out.println("Number of clientes : " + customerRepository.count());
        


    }
    

}
