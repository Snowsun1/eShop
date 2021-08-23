package com.example.eshop.service;

import com.example.eshop.model.User;
import com.example.eshop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;


    User user;

    @BeforeEach
    void setup() {
        user = User.builder()
                .id(1L)
                .name("Alex")
                .surname("Petrov")
                .email("alexpetrov@mail.ru")
                .balance(100.0)
                .address("B. Sadovaya 44")
                .build();

        Mockito.when(userRepository.findUserById(1L))
                .thenReturn(Optional.ofNullable(user));
    }

    @Test
    void getUser() {

    }



    @Test
    void saveUser() {
    }

    @Test
    void editUser() {
    }
}