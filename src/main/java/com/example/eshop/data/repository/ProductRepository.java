package com.example.eshop.data.repository;

import com.example.eshop.data.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Create repository interface for CRUD repository implementations
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<List<Product>> findProductByCountIsAfter(int count);

    Optional<List<Product>> findAllByProductCategoryId(Long productCategory_id);

    Optional<List<Product>> findAllByProductCategoryName(String categoryName);
}
