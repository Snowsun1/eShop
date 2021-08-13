package com.example.eshop.repository;

import com.example.eshop.model.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {
    Optional<ProductCategory> findProductCategoryByName(String name);
}
