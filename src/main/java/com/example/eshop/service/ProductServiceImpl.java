package com.example.eshop.service;

import com.example.eshop.model.Product;
import com.example.eshop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Add implementation for product service.
 */
@Service
@Transactional // looks like @Transactional from Spring is preferable
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct() {
        return new Product();
    }

    @Override
    public Product editProduct(long id) {
        return productRepository.save(productRepository.findById(id).orElseThrow());
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.delete(productRepository.findById(id).orElseThrow());
    }

    @Override
    public Product getProduct(long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
