package com.example.eshop.service;

import com.example.eshop.model.LineOfBasket;
import com.example.eshop.model.Product;

public interface LineOfBasketService {

    void addLine();

    void addProduct(Product product);

    Iterable<LineOfBasket> listLines();
}
