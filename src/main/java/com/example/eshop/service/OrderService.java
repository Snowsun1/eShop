package com.example.eshop.service;

import com.example.eshop.model.Order;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface OrderService {

    // получить список всех заказов
    @NotNull Iterable<Order> getAllOrders();

    // создание заказа
    Order create(@NotNull @Valid Order order);

    // редактирование заказа
    void update(@NotNull @Valid Order order);

    // метод удаления заказа не создаём, поскольку любой заказ сохраняется в истории и нам незачем его удалять
}
