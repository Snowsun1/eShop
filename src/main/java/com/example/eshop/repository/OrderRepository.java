package com.example.eshop.repository;

import com.example.eshop.model.Order;
import com.example.eshop.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Optional<List<Order>> findOrderByDateOfOrder(LocalDate dateOfOrder);
    @Query(value = "select order.* from order join basket on order.basket_id = basket.basket_id " +
            "join user on basket.user_id = ?1 ", nativeQuery = true)
    Optional<List<Order>> findOrdersByUserId(Long userId);
}
