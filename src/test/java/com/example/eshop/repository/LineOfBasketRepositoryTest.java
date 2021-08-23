package com.example.eshop.repository;

import com.example.eshop.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Slf4j
class LineOfBasketRepositoryTest {

    @Autowired
    private LineOfBasketRepository lineOfBasketRepository;
    @Test
//    @Sql("/sql/testScriptForTableDeletion.sql")
    @Sql("/sql/testSqlLineOfBasketRepository.sql")
    void findLineOfBasketByBasketId() {
        log.info("test select findLineOfBasketByBasketId");
        ProductCategory productCategory = ProductCategory.builder()
                .id(1L)
                .name("Молочные продукты")
                .build();
        Product product = Product.builder()
                .id(1L)
                .name("Молоко")
                .count(10)
                .price(4.0)
                .productCategory(productCategory)
                .build();
        LineOfBasket actual = LineOfBasket.builder()
                .id(1L)
                .count(3)
                .positionCost(0.0)
                .product(product)
                .build();
        var lineOfBasket = lineOfBasketRepository.findLineOfBasketByBasketId(1L).orElse(null);
        assert lineOfBasket != null;
        assertEquals(lineOfBasket.get(0), actual);

    }
}