package com.example.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@Entity
@Table(name = "product", schema = "eshop")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Column(name = "product_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

}
