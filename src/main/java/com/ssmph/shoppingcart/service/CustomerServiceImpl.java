package com.ssmph.shoppingcart.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.ssmph.shoppingcart.model.Customer;
import com.ssmph.shoppingcart.repository.CustomerRepository;
import com.ssmph.shoppingcart.repository.OrderRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Optional<Customer> getById(String id) {
        return customerRepository.findById(id);
    }

    @Transactional
    public Customer save(Customer customer, MultipartFile file) throws IOException {

        Storage storage = StorageOptions.getDefaultInstance().getService();

        Customer c = save(customer);

        if (c.getProfileImg()  != null) {
            int i = c.getProfileImg().lastIndexOf("/");
            String fullPath  = c.getProfileImg();
            String profilefileName = fullPath.substring(i+1);

            BlobId blobId = BlobId.of("ssmph-profile-images", profilefileName);        
            storage.delete(blobId);        
        }

        if (file != null) {
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            String fileName = c.getId() + new Date().getTime() + extension;

            BlobId blobId = BlobId.of("ssmph-profile-images", fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            storage.create(blobInfo, file.getBytes());    

            String profileImg = "https://storage.googleapis.com/ssmph-profile-images/" + fileName;
            c.setProfileImg(profileImg);

            save(c);
        }

        return c;
    }

    @Transactional
    public void delete(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        orderRepository.deleteAll(customer.get().getOrders());
        customerRepository.deleteById(id);

        if (customer.get().getProfileImg() != null) {
            int i = customer.get().getProfileImg().lastIndexOf("/");
            String fullPath  = customer.get().getProfileImg();
            String profilefileName = fullPath.substring(i+1);

            Storage storage = StorageOptions.getDefaultInstance().getService();
            BlobId blobId = BlobId.of("ssmph-profile-images", profilefileName);        
            storage.delete(blobId);        
        }

    }

    
    public List<Customer> list() throws DataAccessException {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
    
}
