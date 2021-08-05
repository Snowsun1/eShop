package com.example.eshop.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity(name = "user")
@Data
public class User {
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

    @Column(name = "address", nullable = false)
    private String address;

}
