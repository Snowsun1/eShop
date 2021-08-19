package com.example.eshop.controller;

import com.example.eshop.model.Product;
import com.example.eshop.model.ProductCategory;
import com.example.eshop.model.User;
import com.example.eshop.service.ProductService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private User user;
    private ProductCategory productCategory;
    private Product product;

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
    void testGetProduct() throws Exception {

        Mockito.when(productService
                .getProduct(product.getId()))
                .thenReturn(product);

        MvcResult mvcResult = mockMvc
                .perform(get("/product/1")
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

        Product getProduct = mapFromJson(content, Product.class);
        assertFalse(product == null);
    }

    @Test
    void getProducts() throws Exception {

        Mockito.when(productService.getAllProducts())
                .thenReturn(List.of(product));

        MvcResult mvcResult = mockMvc
                .perform(get("/products")
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

        List<Product> products = mapFromJson(content, List.class);
        assertTrue(products.size() > 0);
    }
}