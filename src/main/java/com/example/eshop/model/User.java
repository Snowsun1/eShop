package com.example.eshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "user", schema = "eshop")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "balance", nullable = false)
    private int balance;

    @Column(name = "address", nullable = false)
    private String address;

}
