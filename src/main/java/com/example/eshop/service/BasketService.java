package com.example.eshop.service;

import com.example.eshop.model.Basket;

public interface BasketService {

    Basket addProduct(Long userId, Long productId, int count);

    Basket removeProduct(Long userId, Long productId, int count);

    Basket getBasket(Long userId);

    Double getTotalCost(Long userId);

    boolean makePayment(Long userId);
}
