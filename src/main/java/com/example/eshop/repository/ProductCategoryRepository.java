package com.example.eshop.repository;

import com.example.eshop.model.ProductCategory;
import org.springframework.data.repository.CrudRepository;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {
}
