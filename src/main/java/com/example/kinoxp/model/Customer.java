package com.example.kinoxp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;
    @Column(unique = true)
    private String email;
    private String password;
    private Date birthday;
    private int phoneNumber;



}
