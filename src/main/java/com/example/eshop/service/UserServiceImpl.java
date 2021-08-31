package com.example.eshop.service;

import com.example.eshop.exception.UserNotFoundException;
import com.example.eshop.data.entity.User;
import com.example.eshop.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException(("Пользователь с id = " + id + " не найден!")));
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
