package com.example.eshop.service;

import com.example.eshop.model.LineOfBasket;
import com.example.eshop.model.User;
import com.example.eshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        userRepository.findById(id);
        return userRepository.findUserById(id).isPresent() ? userRepository.findUserById(id).get() : null;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void editUser(User user, Long id) {
        User thisUser = userRepository.findById(id).orElse(null);
        if (thisUser != null) {
            thisUser.setName(user.getName());
            thisUser.setAddress(user.getAddress());
            thisUser.setEmail(user.getEmail());
            thisUser.setBalance(user.getBalance()); // Стоит ли
            thisUser.setSurname(user.getSurname());
        }
    }
}
