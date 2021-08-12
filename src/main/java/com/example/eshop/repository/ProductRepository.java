package com.example.eshop.repository;

import com.example.eshop.model.Product;
import com.example.eshop.model.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create repository interface for CRUD repository implementations
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findProductByCountIsAfter(int count);

    List<Product> findAllByProductCategory(ProductCategory productCategory);
}
