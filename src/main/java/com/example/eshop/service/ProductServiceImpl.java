package com.example.eshop.service;

import com.example.eshop.exception.ProductNotExists;
import com.example.eshop.model.Product;
import com.example.eshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Add implementation for product service.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product editProduct(Product product, Long id) {
        Product newProduct = productRepository.findById(id).orElse(null);

        if (product != null){
            newProduct.setName(product.getName());
            newProduct.setCount(product.getCount());
            newProduct.setPrice(product.getPrice());
            newProduct.setProductCategory(product.getProductCategory());
        }

        return newProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void increaseCount(long productId, int count) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotExists::new);
        product.setCount(product.getCount() + count);
    }

    @Override
    public void decreaseCount(long productId, int count) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotExists::new);
        product.setCount(product.getCount() - count);
    }
}
