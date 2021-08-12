package com.example.eshop.service;


import com.example.eshop.model.User;

public interface UserService {
    User getUser(Long id);

    void saveUser(User user);

    void editUser(User user, Long id);
}
