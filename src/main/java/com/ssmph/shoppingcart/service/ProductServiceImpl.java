package com.ssmph.shoppingcart.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.ssmph.shoppingcart.model.Product;
import com.ssmph.shoppingcart.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product save(Product product, MultipartFile file) throws IOException {

        Storage storage = StorageOptions.getDefaultInstance().getService();

        Product p = save(product);

        if (p.getImageUrl()  != null) {
            int i = p.getImageUrl().lastIndexOf("/");
            String fullPath  = p.getImageUrl();
            String profilefileName = fullPath.substring(i+1);

            BlobId blobId = BlobId.of("ssmph-profile-images", profilefileName);        
            storage.delete(blobId);        
        }

        if (file != null) {            
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            String fileName = p.getId() + extension;

            
            BlobId blobId = BlobId.of("ssmph-product-images", fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            storage.create(blobInfo, file.getBytes());    

            String profileImg = "https://storage.googleapis.com/ssmph-product-images/" + fileName;
            p.setImageUrl(profileImg);   
            
            save(p);
        }
        
        return p;
    }

    public Product save(Product product) {        
        return productRepository.save(product);
    }

    public List<Product> list() throws DataAccessException {
        return (List<Product>) productRepository.findAll();
    }

    public Optional<Product> getById(String id) {
        return productRepository.findById(id) ;
    }

    @Transactional
    public void delete(String id) {
        Optional<Product> prod = getById(id);
        productRepository.deleteById(id);

        if (prod.get().getImageUrl() != null) {
            int i = prod.get().getImageUrl().lastIndexOf("/");
            String fullPath  = prod.get().getImageUrl();
            String profilefileName = fullPath.substring(i+1);

            Storage storage = StorageOptions.getDefaultInstance().getService();
            BlobId blobId = BlobId.of("ssmph-product-images", profilefileName);        
            storage.delete(blobId);
        }

    }
    
}