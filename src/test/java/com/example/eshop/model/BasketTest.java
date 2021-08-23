package com.example.eshop.model;

import com.example.eshop.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Slf4j
class BasketTest {

    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LineOfBasketRepository lineOfBasketRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
//    @Sql("/sql/testScriptForTableDeletion.sql")
    void simpleSelect(){
        log.info("test select Basket");

        User user = userRepository.save(User.builder()
                .name("Alex")
                .surname("Petrov")
                .email("alex@mail.ru")
                .balance(100.0)
                .address("Zorge 29")
                .build());

        ProductCategory productCategory = productCategoryRepository.save(ProductCategory.builder()
                .name("Молочные продукты")
                .build());

        Product product = productRepository.save(Product.builder()
                .name("Молоко")
                .count(10)
                .price(10.0)
                .productCategory(productCategory)
                .build());

        LineOfBasket lineOfBasket = lineOfBasketRepository.save(LineOfBasket.builder()
                .count(4)
                .product(product)
                .build());
        List<LineOfBasket> lineOfBasketList = new ArrayList<>();
        lineOfBasketList.add(lineOfBasket);
        Basket actual = basketRepository.save(
            Basket.builder()
                    .paid(false)
                    .delivery(false)
                    .user(user)
                    .list(lineOfBasketList)
                    .build()
        );
        Basket expected = Basket.builder()
                .id(actual.getId())
                .paid(false)
                .delivery(false)
                .user(user)
                .list(lineOfBasketList)
                .build();
        assertEquals(expected, actual);
    }
}