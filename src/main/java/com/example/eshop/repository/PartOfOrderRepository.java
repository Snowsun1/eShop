package com.example.eshop.repository;

import com.example.eshop.model.PartOfOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartOfOrderRepository extends CrudRepository<PartOfOrder, Long> {
}
