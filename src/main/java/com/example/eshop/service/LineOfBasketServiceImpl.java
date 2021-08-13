package com.example.eshop.service;

import com.example.eshop.model.LineOfBasket;
import com.example.eshop.model.Product;
import com.example.eshop.repository.LineOfBasketRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
@RequiredArgsConstructor
@Transactional
public class LineOfBasketServiceImpl implements LineOfBasketService{

    private final LineOfBasketRepository lineOfBasketRepository;

    @Override
    public void addLine() {

    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public Iterable<LineOfBasket> listLines() {
        return null;
    }
}
