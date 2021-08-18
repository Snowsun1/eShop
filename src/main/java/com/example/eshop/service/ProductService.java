package com.example.eshop.service;

import com.example.eshop.model.Product;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Create service interface for the products with abstract methods.
 */
@Validated
public interface ProductService {

    Product createProduct(Product product);

    Product editProduct(Product product, Long id);

    void deleteProduct(Long id);

    Product getProduct(@Min(value = 1L) Long id);

    @NotNull
    Iterable<Product> getAllProducts();

    void increaseCount(long productId, int count);

    void decreaseCount(long productId, int count);

    int getAvailableProducts(long productId);
}
