package com.example.eshop.model;

import com.example.eshop.repository.LineOfBasketRepository;
import com.example.eshop.repository.ProductCategoryRepository;
import com.example.eshop.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Slf4j
class LineOfBasketTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private LineOfBasketRepository lineOfBasketRepository;

    @Test
//    @Sql("/sql/testScriptForTableDeletion.sql")
    void simpleSelect() {

        log.info("Test select LineOfBasketRepository");
        ProductCategory productCategory = productCategoryRepository.save(ProductCategory.builder()
                .name("Молочные продукты")
                .build());

        Product product = productRepository.save(Product.builder()
                .name("Молоко")
                .count(10)
                .price(10.0)
                .productCategory(productCategory)
                .build());

        LineOfBasket actual = lineOfBasketRepository.save(LineOfBasket.builder()
                .count(4)
                .product(product)
                .build());

        LineOfBasket expected = LineOfBasket.builder()
                .id(actual.getId())
                .count(4)
                .product(product)
                .build();

        assertEquals(expected, actual);
    }

}