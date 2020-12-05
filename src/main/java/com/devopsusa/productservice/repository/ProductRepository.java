package com.devopsusa.productservice.repository;


import com.devopsusa.productservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductRepository extends JpaRepository<Product,Integer> {

}
