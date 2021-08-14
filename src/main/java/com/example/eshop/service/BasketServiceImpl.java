package com.example.eshop.service;

import com.example.eshop.exception.ProductNotExists;
import com.example.eshop.exception.WrongProductCount;
import com.example.eshop.model.Basket;
import com.example.eshop.model.LineOfBasket;
import com.example.eshop.model.Product;
import com.example.eshop.repository.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
@Transactional
public class BasketServiceImpl implements BasketService{

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final LineOfBasketRepository lineOfBasketRepository;

    @Override
    public Basket addProduct(Long userId, Long productId, int count) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotExists::new);
        Double productPrice = product.getPrice();

        if (count > product.getCount()) throw new WrongProductCount("На складе недостаточное количество товара");
        if (count < 0) throw new WrongProductCount("Недопустимо отрицательное значение количества товара");

        Basket basket = basketRepository.findBasketByUserIdAndPaid(userId, false).orElse(new Basket());

        List<LineOfBasket> lineOfBasketList = basket.getList();

        lineOfBasketList.stream().forEach(lob -> {
            if (lob.getProduct().getId() == product.getId()){
                lob.setCount(lob.getCount() + count);

            }
        });


        for (LineOfBasket lineOfBasket : lineOfBasketList ){
            if (product.getId().equals(lineOfBasket.getProduct().getId())){

            }
        }

        return null;
    }

    @Override
    public Basket removeProduct(Long userId, Long productId, int count) {
        return null;
    }

    @Override
    public Basket getBasket(Long userId) {
        return null;
    }

    @Override
    public Double getTotalCost(Long userId) {
        return null;
    }
}
