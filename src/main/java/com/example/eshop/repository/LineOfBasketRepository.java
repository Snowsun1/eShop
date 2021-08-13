package com.example.eshop.repository;

import com.example.eshop.model.LineOfBasket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineOfBasketRepository extends CrudRepository<LineOfBasket, Long> {
    LineOfBasket findLineOfBasketById(Long id);
    @Query(value = "select * from line_of_basket where basket_id == ?1", nativeQuery = true)
    List<LineOfBasket> findLineOfBasketByBasketId(Long id);
}