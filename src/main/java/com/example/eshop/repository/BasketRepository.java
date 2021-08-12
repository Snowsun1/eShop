package com.example.eshop.repository;

import com.example.eshop.model.Basket;
import com.example.eshop.model.User;
import org.springframework.data.repository.CrudRepository;

public interface BasketRepository extends CrudRepository<Basket, Long> {
    Basket getBasketByUser(User user);
    Basket findBasketById(Long cardId);
    Basket findBasketByUserAndPaid(User user, boolean paid);
    Basket findBasketByUserAndDelivery(User user, boolean delivery);
}
