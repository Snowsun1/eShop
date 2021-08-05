package com.example.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "product_category")
@Data
@AllArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "count", nullable = false)
    private int count;

    // TODO Возможно нужна OneToMany

    public ProductCategory() {

    }
}
