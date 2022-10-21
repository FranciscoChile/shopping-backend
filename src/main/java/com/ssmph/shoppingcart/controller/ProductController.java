package com.ssmph.shoppingcart.controller;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssmph.shoppingcart.model.Product;
import com.ssmph.shoppingcart.model.ProductImage;
import com.ssmph.shoppingcart.service.ProductImagesService;
import com.ssmph.shoppingcart.service.ProductService;
import com.ssmph.shoppingcart.storage.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/api/product")
public class ProductController {
    
	@Value("${ruta.imagenes.path}")
	private String rutaImagenes;
	
    @Autowired
	private ProductService productService;
    
	@Autowired
    private StorageService storageService;

	@Autowired
	private ProductImagesService productImagesService;


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
	
	@PostMapping(value="/multiple-images")
	public void saveProductMultipleImages(
		@RequestParam("product") String p, 
		@RequestParam(value = "files", required=false) MultipartFile[] files
	) throws JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();			
		Product product = mapper.readValue(p, Product.class);
		Product opt = save(product);

		//Optional<Product> opt = productService.getProductById(product.getIdProduct());

		if (files!=null && files.length > 0) {
			storageService.createSubDirectory(product.getSku());

			for (int i=0; i < files.length; i++) {
				MultipartFile file = files[i];

				storageService.store(product.getSku(), file);

				if (opt != null) {
					Product pp = opt;	
					ProductImage pi = new ProductImage();
					pi.setIdProduct(pp.getId());
					pi.setImageProductName(file.getOriginalFilename());
					pi.setSkuProduct(product.getSku());

					productImagesService.saveProductImages(pi);
				}
			}
		
		}

	}
	
}
