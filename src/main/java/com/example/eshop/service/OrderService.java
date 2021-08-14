package com.example.eshop.service;

import com.example.eshop.model.Order;
import java.util.List;


public interface OrderService {

    Order createOrder(Order order);

    Order getOrderById(Long id);

    Order updateOrder(Order order, Long id);

    void deleteOrder(Order order);

    Iterable<Order> getOrderList();
}
