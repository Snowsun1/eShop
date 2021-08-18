package com.example.eshop.service;

import com.example.eshop.model.Product;
import com.example.eshop.model.ProductCategory;
import com.example.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductCategory productCategory;

    @MockBean
    private Product product;

    @BeforeEach
    void setUp() {

        productCategory = ProductCategory
                .builder()
                .name("Канцелярские товары")
                .build();

        product = Product
                .builder()
                .id(1L)
                .name("Карандаши")
                .count(10)
                .price(100.0)
                .productCategory(productCategory)
                .build();

        Mockito.when(productRepository.save(product))
                .thenReturn(product);

        Mockito.when(productRepository.findById(product.getId()))
                .thenReturn(Optional.ofNullable(product));
    }

    @Test
    void testGetAvailableProducts() {
        Optional<Product> optionalProduct = productRepository
                .findById(product.getId());
        optionalProduct.ifPresent(value -> assertEquals(product, value));
        int expectedProducts = 10;
        int availableProducts = productService
                .getAvailableProducts(product.getId());
        assertEquals(expectedProducts, availableProducts);
    }

    @Test
    void testCreateProduct() {

        Product actualProduct = productRepository.save(product);
        assertEquals(product, actualProduct);
    }

    @Test
    void testEditProduct() {

        Optional<Product> optionalProduct = productRepository
                .findById(product.getId());
        optionalProduct.ifPresent(value -> assertEquals(product, value));
        Product actualProduct = productService
                .editProduct(product, product.getId());
        assertEquals(product, actualProduct);
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getProduct() {
        Product actualProduct = productService
                .getProduct(product.getId());
        assertEquals(product, actualProduct);
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void increaseCount() {
    }

    @Test
    void decreaseCount() {
    }

    @Test
    void getAvailableProducts() {
    }
}