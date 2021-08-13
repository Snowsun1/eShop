package com.example.eshop.controller;

import com.example.eshop.model.Product;
import com.example.eshop.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * Create REST controller for the products.
 */

@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }

    @GetMapping("/products")
    public @NotNull Iterable<Product> getProducts(){
        return productService.getAllProducts();
    }

    // to do: add post, get and put requests for other methods.
}
