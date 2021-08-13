package com.example.eshop.service;

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
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final LineOfBasketRepository lineOfBasketRepository;

    @Override
    public Basket add(Long basketId, Product product, int count) {

        Basket basket = basketRepository.findBasketById(basketId);
        LineOfBasket line = new LineOfBasket();

        // проверяем наличие товара в корзине и при наличии увеличиваем на заданное число
        if (basket.getBankCards().stream().anyMatch(i -> i.getProduct() == product)){
            basket.getBankCards().forEach(i ->{
                if (i.getProduct() == product){
                    i.setCount(i.getCount() + count);
                }
            });
        } else {
            // если товара нет в списке, то добавляем его
            line.setProduct(product);
            basket.getBankCards().add(line);
        }

        return basketRepository.save(basket);
    }

    @Override
    public Basket update(Long basketId, Product product, int count) {

        // Находим корзину по Id
        Basket basket = basketRepository.findBasketById(basketId);

        // если корзина есть
        if (basket != null){
            basket.getBankCards().forEach(i -> {
                if (i.getProduct() == product){
                    i.setCount(count);
                }
            });
            basketRepository.save(basket);
        } else {
            // если корзины нет, то возвращаем пустую корзину
            return new Basket();
        }

        return basket;
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

        return values.stream().reduce(0.0, Double::sum);
    }
}
