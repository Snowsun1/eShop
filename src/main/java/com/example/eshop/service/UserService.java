package com.example.eshop.service;


import com.example.eshop.data.entity.User;

public interface UserService {
    User getUser(Long id);

    void saveUser(User user);
}
