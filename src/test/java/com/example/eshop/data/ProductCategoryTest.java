package com.example.eshop.data;

import com.example.eshop.data.entity.ProductCategory;
import com.example.eshop.data.repository.ProductCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Slf4j
class ProductCategoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
//    @Sql("/sql/testScriptForTableDeletion.sql")
    void simpleSelect(){

        log.info("Test select ProductCategoryRepository");
        ProductCategory actual = productCategoryRepository.save(ProductCategory.builder()
                .name("Молочные продукты")
                .build());

        ProductCategory expected = ProductCategory.builder()
                .id(actual.getId())
                .name("Молочные продукты")
                .build();

        assertEquals(expected, actual);
    }
}