package com.example.eshop.service;

import com.example.eshop.model.Basket;
import com.example.eshop.model.Product;

public interface BasketService {

    Basket addProduct(Long userId, Long productId, int count);

    Basket removeProduct(Long userId, Long productId, int count);

    Basket getBasket(Long userId);

    Double getTotalCost(Long userId);
}
