package com.example.eshop.repository;

import com.example.eshop.model.Basket;
import com.example.eshop.model.LineOfBasket;
import com.example.eshop.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Long> {
    Optional<List<Basket>> findBasketByUserId(Long userId);
    Optional<Basket> findBasketById(Long cardId);
    Optional<Basket> findBasketByUserIdAndPaid(Long userId, boolean paid);
    Optional<Basket> findBasketByUserIdAndDelivery(Long userId, boolean delivery);
}
