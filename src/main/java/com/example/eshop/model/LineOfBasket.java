package com.example.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "line_of_basket", schema = "eshop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineOfBasket {
    @Column(name = "lob_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int count;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "position_cost")
    float positionCost;
}
