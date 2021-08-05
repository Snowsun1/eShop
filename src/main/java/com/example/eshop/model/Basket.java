package com.example.eshop.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "basket")
@Data
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
