package com.example.eshop.controller;

import com.example.eshop.model.Basket;
import com.example.eshop.model.Product;
import com.example.eshop.service.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @PostMapping(value = "/basket/{basketId}/{product}/{count}")
    public Basket add(@PathVariable Long basketId,
                      @PathVariable Product product,
                      @PathVariable int count){
        return basketService.add(basketId, product, count);
    }

    @PutMapping(value = "/basket/{basketId}/{product}/{count}")
    public Basket update(@PathVariable Long basketId,
                         @PathVariable Product product,
                         @PathVariable int count){
        return basketService.update(basketId, product, count);
    }
}
