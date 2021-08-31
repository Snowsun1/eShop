package com.example.eshop.service;

import com.example.eshop.exception.UserNotFoundException;
import com.example.eshop.data.entity.User;
import com.example.eshop.data.repository.UserRepository;
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


    private User user;

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
        User optionalUser = userRepository.findUserById(1L).orElseThrow(()->new UserNotFoundException("Юзер с таким айди не найден"));
        User actual = userService.getUser(optionalUser.getId());
        assertEquals(actual, optionalUser);
    }



    @Test
    void saveUser() {
        userService.saveUser(user);
        assertNotNull(userRepository.findAll());
    }
}