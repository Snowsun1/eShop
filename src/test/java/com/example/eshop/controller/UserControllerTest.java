package com.example.eshop.controller;

import com.example.eshop.data.entity.User;
import com.example.eshop.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user;
    @BeforeEach
    void setup(){
        user = User.builder()
                .id(1L)
                .name("Alex")
                .surname("Petrov")
                .email("alexpetrov@mail.ru")
                .balance(100.0)
                .address("B. Sadovaya 44")
                .build();
    }
    <T> T mapFromJson(String json, Class<T> tClass) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, tClass);
    }

    String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void getUser() throws Exception {
        Mockito.when(userService.getUser(1L))
                .thenReturn(user);

        MvcResult mvcResult = mockMvc
                .perform(get("/user/1")
                    .contentType(MediaType
                            .APPLICATION_JSON))
                .andReturn();
        int status = mvcResult
                .getResponse()
                .getStatus();
        assertEquals(200, status);
        String content = mvcResult
                .getResponse()
                .getContentAsString();

        User getUser = mapFromJson(content, User.class);
        assertNotNull(getUser);
    }

    @Test
    void createUser() throws Exception {
        MvcResult mvcResult =  mockMvc
                .perform(post("/create-user")
                        .contentType(MediaType.APPLICATION_JSON).content(mapToJson(user)))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }

    @Test
    void editUser() throws Exception {
        MvcResult mvcResult =  mockMvc
                .perform(post("/create-user")
                        .contentType(MediaType.APPLICATION_JSON).content(mapToJson(user)))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}