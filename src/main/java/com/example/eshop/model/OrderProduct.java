package com.example.eshop.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class OrderProduct {

    @EmbeddedId
    private OrderProductBasket basket;

    @Column(nullable = false)
    private Integer quantity;

    public OrderProduct() {
        super();
    }

    public OrderProduct(Order order, Product product, Integer quantity) {
        basket = new OrderProductBasket();
        basket.setOrder(order);
        basket.setProduct(product);
        this.quantity = quantity;
    }

    public OrderProductBasket getBasket() {
        return basket;
    }

    public void setBasket(OrderProductBasket basket) {
        this.basket = basket;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Transient
    public Product getProduct(){
        return this.basket.getProduct();
    }

    @Transient
    public Double getTotalPrice(){
        return getProduct().getPrice() * getQuantity();
    }
}
