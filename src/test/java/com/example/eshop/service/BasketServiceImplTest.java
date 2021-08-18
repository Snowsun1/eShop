package com.example.eshop.service;

import com.example.eshop.model.Basket;
import com.example.eshop.model.User;
import com.example.eshop.repository.BasketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BasketServiceImplTest {

    @Autowired
    private BasketService basketService;

    @MockBean
    private User user;

    @MockBean
    private Basket basket;

    @MockBean
    private BasketRepository basketRepository;

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

        basket = Basket
                .builder()
                .id(1L)
                .totalCost(600.0)
                .paid(false)
                .delivery(false)
                .user(user)
                .build();

        Mockito.when(basketRepository.findBasketByUserIdAndPaid(
                        basket.getUser().getId(), basket.isPaid()))
                .thenReturn(Optional.ofNullable(basket));
    }

    @Test
    void addProduct() {
    }

    @Test
    void removeProduct() {
    }

    @Test
    void getBasket() {
    }

    @Test
    void getTotalCost() {
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