package com.example.eshop.data;

import com.example.eshop.data.entity.Product;
import com.example.eshop.data.entity.ProductCategory;
import com.example.eshop.data.repository.ProductCategoryRepository;
import com.example.eshop.data.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Slf4j
class ProductTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
//    @Sql("/sql/testScriptForTableDeletion.sql")
    void simpleSelect() {

        log.info("Test select ProductRepository");
        ProductCategory productCategory = productCategoryRepository.save(ProductCategory.builder()
                .name("Молочные продукты")
                .build());

        Product actual = productRepository.save(Product.builder()
                .name("Молоко")
                .count(10)
                .price(10.0)
                .productCategory(productCategory)
                .build());

        Product expected = Product.builder()
                .id(actual.getId())
                .name("Молоко")
                .count(10)
                .price(10.0)
                .productCategory(productCategory)
                .build();
        assertEquals(expected, actual);
    }
}