package com.example.eshop.repository;

import com.example.eshop.model.Basket;
import com.example.eshop.model.LineOfBasket;
import com.example.eshop.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Long> {
//    @Query(value = "select * from basket where user_id == 1?", nativeQuery = true)
    List<Basket> findBasketByUserId(Long userId);
    Basket findBasketById(Long cardId);
//    @Query(value = "select * from basket where user_id == 1? and paid == 2?", nativeQuery = true)
    Basket findBasketByUserIdAndPaid(Long userId, boolean paid);
//    @Query(value = "select  * from basket where user_id == 1? and delivery == 2?", nativeQuery = true)
    Basket findBasketByUserIdAndDelivery(Long userId, boolean delivery);
}
