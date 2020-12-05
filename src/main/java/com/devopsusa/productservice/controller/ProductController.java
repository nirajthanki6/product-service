package com.devopsusa.productservice.controller;

import com.devopsusa.productservice.exception.RecordNotFoundException;
import com.devopsusa.productservice.service.ProductService;
import com.devopsusa.productservice.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productservice;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productservice.getallproducts();
        return new ResponseEntity<List<Product>>(products,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) throws RecordNotFoundException {
        Product product = productservice.getProductById(id);
        return new ResponseEntity<Product>(product,new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createOrUpdateProduct(@RequestBody Product product) throws RecordNotFoundException{
        Product neworupdatedproduct = productservice.createOrUpdateProduct(product);
        return new ResponseEntity<Product>(neworupdatedproduct,new HttpHeaders(),HttpStatus.OK);
    }
}
