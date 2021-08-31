package com.example.eshop.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "basket", schema = "eshop")
@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    @Column(name = "basket_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_cost")
    private double cost;

    @Column
    private Boolean paid;

    @Column
    private Boolean delivery;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "user_id") // oto
    private User user;

    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "basket_id")
    private List<LineOfBasket> list;
}
