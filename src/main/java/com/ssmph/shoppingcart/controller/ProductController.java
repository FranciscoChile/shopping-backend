package com.ssmph.shoppingcart.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.ssmph.shoppingcart.model.Product;
import com.ssmph.shoppingcart.service.ProductService;

//@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/product")
public class ProductController {
    
	@Value("${ruta.imagenes.path}")
	private String rutaImagenes;
	
    @Autowired
	private ProductService productService;


    @GetMapping
	@ResponseStatus(value = HttpStatus.OK)
    public List<Product> list() {
		try {
	    	return productService.list(); 	
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
    }	

    @GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Product get(@PathVariable String id) {
		try {
			Optional<Product> product = productService.getById(id);
			return product.get();	
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
    
	@DeleteMapping(value="/{id}")
    public void delete(@PathVariable String id) {
		try {
			productService.delete(id);
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
    }
    
	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
    public Product save(@RequestBody Product p){
		try {
			return productService.save(p);	
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
    }
	
	@PostMapping(value="/save-multiple-images")
	@ResponseStatus(value = HttpStatus.OK)
	public Product saveWithImages(
		@RequestParam("product") String p, 
		@RequestParam(value = "image", required=false) MultipartFile[] files
	) throws IOException {

		ObjectMapper mapper = new ObjectMapper();			
		Product c = mapper.readValue(p, Product.class);

		if (files == null) {
			return productService.save(c);
		}
		else {
			return productService.save(c, files[0]);
		}
		

	}
	
}
