package com.example.eshop.repository;

import com.example.eshop.data.entity.*;
import com.example.eshop.data.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Slf4j
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Sql("/sql/testSqlOrderRepository.sql")
    void findOrdersByUserId() throws Exception {
        log.info("Repository test find order by user id");
        List<Order> orderList = orderRepository.findOrdersByUserId(1L).orElseThrow(RuntimeException::new);
        User user = User.builder()
                .id(1L)
                .name("Alex")
                .surname("Petrov")
                .email("alex@mail.ru")
                .balance(100.0)
                .address("sadovaya 1")
                .build();

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

        LineOfBasket lineOfBasket = LineOfBasket.builder()
                .id(1L)
                .count(3)
                .product(product)
                .positionCost(1.0)
                .build();

        List<LineOfBasket> lineOfBasketList = Collections.singletonList(lineOfBasket);

        Basket basket = Basket.builder()
                .id(1L)
                .paid(false)
                .delivery(false)
                .user(user)
                .list(lineOfBasketList)
                .build();

        Order actual = Order.builder()
                .id(1L)
                .dateOfOrder(orderList.get(0).getDateOfOrder())
                .basket(basket)
                .build();

        Order order = orderList.get(0);

        System.out.println(orderList.get(0).getBasket().getList().hashCode());
        System.out.println(actual.getBasket().getList().hashCode());
        assertEquals(actual.getId(),orderList.get(0).getId());


    }
}