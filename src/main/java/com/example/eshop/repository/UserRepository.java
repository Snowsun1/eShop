package com.example.eshop.repository;

import com.example.eshop.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserById(Long userId);
}
