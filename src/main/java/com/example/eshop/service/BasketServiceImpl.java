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
        Product product = productRepository
                .findById(productId)
                .orElseThrow(ProductNotExists::new);
        double productPrice = product.getPrice();

        if (count > product.getCount()) throw new WrongProductCount("На складе недостаточное количество товара");
        if (count < 0) throw new WrongProductCount("Недопустимо отрицательное значение количества товара");

        Basket basket = basketRepository
                .findBasketByUserIdAndPaid(userId, false)
                .orElse(new Basket());

        List<LineOfBasket> lineOfBasketList = basket.getList();

        for (LineOfBasket lob : lineOfBasketList ){
            if (product.getId().equals(lob.getProduct().getId())){
                lob.setCount(lob.getCount() + count);
                lob.setPositionCost((lob.getCount() + count)
                        * productPrice);
                // уменьшить количество товара на складе
                product.setCount(product.getCount() - count);
                productRepository.save(product);
                basketRepository.save(basket);
                return basket;
            }
        }

        // если таки продукта в корзине не оказалось
        LineOfBasket line = new LineOfBasket();
        line.setProduct(product);
        line.setCount(count);
        line.setPositionCost(count * productPrice);

        // уменьшить количество товара на складе
        product.setCount(product.getCount() - count);
        productRepository.save(product);

        basket.getList().add(line);
        basketRepository.save(basket);

        return basket;
    }

    @Override
    public Basket removeProduct(Long userId, Long productId, int count) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotExists::new);
        double productPrice = product.getPrice();

        Basket basket = basketRepository.findBasketByUserIdAndPaid(userId, false).orElse(new Basket());
        List<LineOfBasket> lineOfBasketList = basket.getList();

        if (count < 0) throw new WrongProductCount("Недопустимо отрицательное значение количества товара");

        for (LineOfBasket lob : lineOfBasketList ){
            if (count > lob.getCount()) throw new WrongProductCount("В корзине недостаточное количество товара");
            if (product.getId().equals(lob.getProduct().getId())){
                lob.setCount(lob.getCount() - count);
                lob.setPositionCost((lob.getCount() - count) * productPrice);
                product.setCount(product.getCount() + count);
                productRepository.save(product);
                basketRepository.save(basket);
                return basket;
            }
        }
         basketRepository.save(basket);
         return basket;
    }

    @Override
    public Basket getBasket(Long userId) {
        return basketRepository
                .findBasketByUserIdAndPaid(userId, false)
                .orElse(new Basket());
    }

    @Override
    public Double getTotalCost(Long userId) {
        Basket basket = basketRepository
                .findBasketByUserIdAndPaid(userId, false)
                .orElse(new Basket());
        List<LineOfBasket> lineOfBasketList = basket.getList();
        return lineOfBasketList
                .stream()
                .map(LineOfBasket::getPositionCost)
                .reduce(0.0, Double::sum);
    }

    @Override
    public boolean makePayment(Long userId){
        Optional<Basket> optionalBasket = basketRepository
                .findBasketByUserIdAndPaid(userId, false);
        if (optionalBasket.isPresent()){
            Basket basket = optionalBasket.get();
            basket.setPaid(true);
            basketRepository.save(basket);
            return true;
        }
        return false;
    }
}
