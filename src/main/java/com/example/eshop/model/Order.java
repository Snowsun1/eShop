package com.example.eshop.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "order", schema = "eshop")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Column(name = "order_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "date_of_order", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfOrder;
    @OneToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;


}
