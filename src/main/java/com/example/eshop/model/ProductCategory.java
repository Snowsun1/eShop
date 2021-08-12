package com.example.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product_category", schema = "eshop")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory {
    @Column(name = "product_category_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    // TODO Возможно нужна OneToMany
}
