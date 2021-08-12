package com.example.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
        @Table(name = "part_of_order", schema = "eshop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartOfOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int count;
    @Column(name = "product_id")
    private Long productId;
}
