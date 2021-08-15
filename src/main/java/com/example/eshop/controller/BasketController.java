package com.example.eshop.controller;

import com.example.eshop.model.Basket;
import com.example.eshop.service.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @PostMapping(value = "/basket/{userId}/{productId}/{count}")
    public Basket add(@PathVariable long userId,
                      @PathVariable long productId,
                      @PathVariable int count){
        return basketService.addProduct(userId, productId, count);
    }

    @PutMapping(value = "/basket/{userId}/{productId}/{count}")
    public Basket update(@PathVariable long userId,
                         @PathVariable long productId,
                         @PathVariable int count){
        return basketService.removeProduct(userId, productId, count);
    }

    @GetMapping(value = "/basket/{userId}")
    public Basket getBasket(@PathVariable long userId){
        return basketService.getBasket(userId);
    }

    @GetMapping(value = "/basket/TotalCost/{userId}")
    public double getTotalCost(@PathVariable long userId){
        return basketService.getTotalCost(userId);
    }
}
