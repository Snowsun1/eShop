package com.example.eshop.repository;

import com.example.eshop.data.entity.LineOfBasket;
import com.example.eshop.data.entity.Product;
import com.example.eshop.data.entity.ProductCategory;
import com.example.eshop.data.repository.LineOfBasketRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Slf4j
class LineOfBasketRepositoryTest {

    @Autowired
    private LineOfBasketRepository lineOfBasketRepository;
    @Test
    @Sql(scripts = {//"/sql/testScriptForTableDeletion.sql",
            "/sql/testSqlLineOfBasketRepository.sql"})
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
                .product(product)
                .positionCost(1.0)
                .build();
        List<LineOfBasket> lineOfBasket = lineOfBasketRepository.findLineOfBasketByBasketId(1L).orElseThrow(RuntimeException::new);
        assertEquals(lineOfBasket.get(0), actual);

    }
}