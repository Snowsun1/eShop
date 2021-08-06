package com.example.eshop.repository;

import com.example.eshop.model.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Create repository interface for CRUD repository implementations
 */
public interface ProductRepository extends CrudRepository<Product, Long> {
}
