package com.example.eshop.service;

import com.example.eshop.model.Order;
import com.example.eshop.repository.OrderRepository;
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
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    public Order updateOrder(Order order, Long id) {
        Order newOrder = orderRepository.findById(id).orElse(null);

        if (newOrder != null){
            newOrder.setDateOfOrder(order.getDateOfOrder());
            newOrder.setBasket(order.getBasket());
        }

        return newOrder;
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public Iterable<Order> getOrderList() {
        return orderRepository.findAll();
    }
}
