package com.example.eshop.repository;

import com.example.eshop.model.Order;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> getOrderByDateOfOrder(LocalDate dateOfOrder);

}
