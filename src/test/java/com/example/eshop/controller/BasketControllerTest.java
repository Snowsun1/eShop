package com.example.eshop.controller;

import com.example.eshop.model.Basket;
import com.example.eshop.model.Product;
import com.example.eshop.model.ProductCategory;
import com.example.eshop.model.User;
import com.example.eshop.service.BasketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BasketController.class)
class BasketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BasketService basketService;

    private User user;
    private ProductCategory productCategory;
    private Product product;
    private Basket basket;

    @BeforeEach
    void setUp() {

        user = User.builder()
                .id(1L)
                .name("James")
                .surname("Bond")
                .email("agent007@gmail.com")
                .balance(500.0)
                .address("MI-6")
                .build();

        productCategory = ProductCategory
                .builder()
                .name("Канцелярские товары")
                .build();

        product = Product
                .builder()
                .id(1L)
                .name("Карандаши")
                .count(10)
                .price(100.0)
                .productCategory(productCategory)
                .build();

        basket = Basket
                .builder()
                .id(1L)
                .totalCost(600.0)
                .paid(false)
                .delivery(false)
                .user(user)
                .build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> tClass)
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, tClass);
    }

    @Test
    void testMakePayment() throws Exception {

        Mockito.when(basketService
                .makePayment(user.getId()))
                .thenReturn(true);

        mockMvc.perform(
                post("/basket/makePayment/1")
                        .contentType(MediaType
                                .APPLICATION_JSON)
                        .content(""))
                .andExpect(status()
                        .isOk());
    }

    @Test
    void testAdd() throws Exception {
        String inputJson = mapToJson(basket);
        MvcResult mvcResult = mockMvc
                .perform(post("/basket/1/1/5")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void testUpdate() throws Exception {
        String inputJson = mapToJson(basket);
        MvcResult mvcResult = mockMvc
                .perform(put("/basket/1/1/5")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void testGetBasket() throws Exception {

        Mockito.when(basketService.getBasket(user.getId()))
                .thenReturn(basket);

        MvcResult mvcResult = mockMvc
                .perform(get("/basket/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);

        String content = mvcResult
                .getResponse()
                .getContentAsString();

        Basket basket = mapFromJson(content, Basket.class);
        assertNotNull(basket);
    }

    @Test
    void testGetTotalCost() throws Exception {

        Mockito.when(basketService.getTotalCost(user.getId()))
                .thenReturn(600.0);



        MvcResult mvcResult = mockMvc
                .perform(get("/basket/TotalCost/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();

        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();

        Double totalCost = Double.valueOf(content);
        assertEquals(600.0, totalCost);

    }
}