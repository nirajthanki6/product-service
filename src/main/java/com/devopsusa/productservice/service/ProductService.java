package com.devopsusa.productservice.service;

import com.devopsusa.productservice.domain.Product;
import com.devopsusa.productservice.exception.RecordNotFoundException;
import com.devopsusa.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getallproducts() {
        List<Product> products = productRepository.findAll();
        if (products.size() > 0) {
            return products;
        } else {
            return new ArrayList<Product>();
        }
    }

    public Product getProductById(Integer id) throws RecordNotFoundException {
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()) {
            return product.get();
        } else {
            throw new RecordNotFoundException("No product record exist for given id");
        }
    }

    public Product createOrUpdateProduct(Product product) throws RecordNotFoundException {
        Optional<Product> neworupdateproduct = productRepository.findById(product.getId());
        if(neworupdateproduct.isPresent()){
            Product updateproduct = neworupdateproduct.get();
            updateproduct.setName(product.getName());
            updateproduct.setDescription(product.getDescription());
            updateproduct.setQuantity(product.getQuantity());
            updateproduct = productRepository.save(updateproduct);
            return updateproduct;
        } else {
            product.setId(1);
            product = productRepository.save(product);
            return product;
        }
    }
}
