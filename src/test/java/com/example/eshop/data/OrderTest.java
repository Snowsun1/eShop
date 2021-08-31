package com.example.eshop.data;

import com.example.eshop.data.entity.*;
import com.example.eshop.data.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Slf4j
class OrderTest {
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
    @Autowired
    private OrderRepository orderRepository;

    @Test
//    @Sql("/sql/testScriptForTableDeletion.sql")
    void simpleSelect() {
        log.info("test select Basket");

        User user = userRepository.save(
                User.builder()
                        .name("Alex")
                        .surname("Petrov")
                        .email("alex@mail.ru")
                        .balance(100.0)
                        .address("Zorge 29")
                        .build());

        ProductCategory productCategory = productCategoryRepository.save(
                ProductCategory.builder()
                        .name("Молочные продукты")
                        .build());

        Product product = productRepository.save(
                Product.builder()
                        .name("Молоко")
                        .count(10)
                        .price(10.0)
                        .productCategory(productCategory)
                        .build());

        LineOfBasket lineOfBasket = lineOfBasketRepository.save(
                LineOfBasket.builder()
                        .count(4)
                        .product(product)
                        .build());

        List<LineOfBasket> lineOfBasketList = Collections.singletonList(lineOfBasket);

        Basket basket = basketRepository.save(
                Basket.builder()
                        .paid(false)
                        .delivery(false)
                        .user(user)
                        .list(lineOfBasketList)
                        .build());

        Order actual = orderRepository.save(Order.builder()
                .dateOfOrder(LocalDate.now())
                .basket(basket)
                .build());

        Order expected = Order.builder()
                .id(actual.getId())
                .dateOfOrder(actual.getDateOfOrder())
                .basket(basket)
                .build();
        assertEquals(expected, actual);
    }
}

