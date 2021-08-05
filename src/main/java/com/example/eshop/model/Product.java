package com.example.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@Entity(name = "product")
@Data
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    public Product() {

    }
}
