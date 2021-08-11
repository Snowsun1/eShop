package com.example.eshop.service;

import com.example.eshop.model.User;
import com.example.eshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        return userRepository.findUserById(id);
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
            thisUser.setSurname(user.getSurname());
        }
    }
}
