package com.example.eshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "baskets")
@Data
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double cost;

    private boolean paid;

    private boolean delivery;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "user_id") // oto
    private User user;

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "order_id")
    private List<LineOfBasket> bankCards = new ArrayList<>();
}
