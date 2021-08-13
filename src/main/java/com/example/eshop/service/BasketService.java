package com.example.eshop.service;

import com.example.eshop.model.Basket;
import com.example.eshop.model.Product;

public interface BasketService {

    Basket add(Long basketId, Product product, int count);

    Basket update(Long basketId, Product product, int count);

    void delete(Basket basket, Product product, int count);

    Double getTotal(Basket basket);
}
