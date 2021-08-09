package com.example.eshop.repository;

import com.example.eshop.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findProductById(Long id);

}
