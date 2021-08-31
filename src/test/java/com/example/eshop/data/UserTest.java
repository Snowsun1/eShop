package com.example.eshop.data;

import com.example.eshop.data.entity.User;
import com.example.eshop.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Slf4j
class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
//    @Sql("/sql/testScriptForTableDeletion.sql")
    void simpleSelect() {

        log.info("test select User");
        User actual = userRepository.save(User.builder()
                .name("Alex")
                .surname("Petrov")
                .email("alex@mail.ru")
                .balance(100.0)
                .address("Zorge 29")
                .build());


        User expected = User.builder()
                .id(actual.getId())
                .name("Alex")
                .surname("Petrov")
                .email("alex@mail.ru")
                .balance(100.0)
                .address("Zorge 29")
                .build();
        assertEquals(expected, actual);
    }
}
