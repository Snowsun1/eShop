package com.example.eshop.service;

import com.example.eshop.model.Basket;
import com.example.eshop.model.LineOfBasket;
import com.example.eshop.model.Product;
import com.example.eshop.repository.BasketRepository;
import com.example.eshop.repository.OrderRepository;
import com.example.eshop.repository.ProductRepository;
import com.example.eshop.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
@Transactional
public class BasketServiceImpl implements BasketService{

    private final BasketRepository basketRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public Basket add(Long basketId, Product product, int count) {


        // доделать
        Product product1 = productRepository.findById(product.getId()).orElse(null);
        // if (product != null) LineOfBasket lineOfBasket = new LineOfBasket(product, count);

        return null;
    }

    @Override
    public Basket update(Basket basket, Product product, int count) {
        return null;
    }

    @Override
    public void delete(Basket basket, Product product, int count) {

    }

    @Override
    public Double getTotal(Basket basket) {

        return getBasketPrice(basket);
    }

    private Double getBasketPrice(Basket basket) {

        List<Double> values = new ArrayList<>();
        basket.getBankCards().forEach(lineOfBasket ->
                values.add(lineOfBasket.getProduct().getPrice() * lineOfBasket.getCount()));
        Double sum = values.stream().reduce(0.0, Double::sum);
        return sum;
    }
}
