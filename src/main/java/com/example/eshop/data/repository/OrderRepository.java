package com.example.eshop.data.repository;

import com.example.eshop.data.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Optional<List<Order>> findOrderByDateOfOrder(LocalDate dateOfOrder);
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query(value = "select * from eshop.aorder join eshop.basket on aorder.basket_id = basket.basket_id " +
            "join eshop.auser on basket.user_id = ?1 ", nativeQuery = true)
    Optional<List<Order>> findOrdersByUserId(Long userId);
}
