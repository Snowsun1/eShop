package com.example.eshop.service;

import com.example.eshop.model.*;
import com.example.eshop.repository.BasketRepository;
import com.example.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BasketServiceImplTest {

    @Autowired
    private BasketService basketService;

    private User user;
    private ProductCategory productCategory;
    private Product product, otherProduct;
    private LineOfBasket lineOfBasket;
    private Basket basket, emptyBasket;

    @MockBean
    private BasketRepository basketRepository;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {

        user = User.builder()
                .id(1L)
                .name("James")
                .surname("Bond")
                .email("agent007@gmail.com")
                .balance(500.0)
                .address("MI-6")
                .build();

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

        otherProduct = Product
                .builder()
                .id(2L)
                .name("Тетради")
                .count(10)
                .price(100.0)
                .productCategory(productCategory)
                .build();

        lineOfBasket = LineOfBasket
                .builder()
                .id(1L)
                .count(7)
                .product(product)
                .positionCost(700.0)
                .build();

        basket = Basket
                .builder()
                .id(1L)
                .totalCost(600.0)
                .paid(false)
                .delivery(false)
                .user(user)
                .list(List.of(lineOfBasket))
                .build();

        emptyBasket = Basket
                .builder()
                .id(2L)
                .totalCost(600.0)
                .paid(false)
                .delivery(false)
                .user(user)
                .list(Collections.emptyList())
                .build();

        Mockito.when(basketRepository.findBasketByUserIdAndPaid(
                        basket.getUser().getId(), basket.isPaid()))
                .thenReturn(Optional.ofNullable(basket));
    }

    @Test
    void testAddProduct() {
        Mockito.when(productRepository.findById(product.getId()))
                .thenReturn(Optional.ofNullable(product));
        Mockito.when(basketRepository
                .findBasketByUserIdAndPaid(user.getId(), basket.isPaid()))
                .thenReturn(Optional.ofNullable(basket));
        Basket basketAddProduct = basketService
                .addProduct(user.getId(), product.getId(), 5);
        int count = basketAddProduct.getList().get(0).getCount();
        assertEquals(12, count);
    }


    @Test
    void testRemoveProduct() {
        Mockito.when(productRepository.findById(product.getId()))
                .thenReturn(Optional.ofNullable(product));
        Mockito.when(basketRepository
                .findBasketByUserIdAndPaid(user.getId(), basket.isPaid()))
                .thenReturn(Optional.ofNullable(basket));
        Basket basketDecrProduct = basketService
                .removeProduct(user.getId(), product.getId(), 5);
        int count = basketDecrProduct.getList().get(0).getCount();
        assertEquals(2, count);
    }

    @Test
    void testGetBasket() {
        Mockito.when(basketRepository
                .findBasketByUserIdAndPaid(user.getId(), basket.isPaid()))
                .thenReturn(Optional.ofNullable(basket));
        Basket actualBasket = basketService.getBasket(user.getId());
        List<LineOfBasket> list = actualBasket.getList();
        assertTrue(list.size() > 0);
    }

    @Test
    void testGetTotalCost() {
        Mockito.when(basketRepository
                .findBasketByUserIdAndPaid(user.getId(), basket.isPaid()))
                .thenReturn(Optional.ofNullable(basket));
        Double totalCost = basketService.getTotalCost(user.getId());
        assertEquals(700.0, totalCost);
    }

    @Test
    void makePayment() {

        Optional<Basket> optionalBasket = basketRepository.findBasketByUserIdAndPaid(
                basket.getUser().getId(),
                basket.isPaid());
        if (optionalBasket.isPresent()){
            assertEquals(basket, optionalBasket.get());
            basketService.makePayment(optionalBasket.get().getUser().getId());
            assertTrue(optionalBasket.get().isPaid());
        }
    }
}