package com.ssmph.shoppingcart.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssmph.shoppingcart.model.Customer;
import com.ssmph.shoppingcart.service.CustomerService;

//@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Customer> list() {
        try {
    	    return customerService.list(); 	
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }	

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
	public Customer get(@PathVariable String id) {
        try {
            Optional<Customer> customer = customerService.getById(id);
            return customer.get();    
        } catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
	}

	@DeleteMapping(value="/{id}")
    public void delete(@PathVariable String id) {
        try {
            customerService.delete(id);
        } catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

	@PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Customer save(@RequestBody Customer c){
        try {
            return customerService.save(c);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(value="/save-profile-image")
    @ResponseStatus(value = HttpStatus.OK)
	public Customer saveWithProfileImage(
		@RequestParam("customer") String p, 
		@RequestParam(value = "image", required=false) MultipartFile file
	) throws IOException {

		ObjectMapper mapper = new ObjectMapper();			
		Customer c = mapper.readValue(p, Customer.class);
		return customerService.save(c, file);

	}

}
