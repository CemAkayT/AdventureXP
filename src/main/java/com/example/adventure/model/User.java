package com.example.adventure.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    public User() {
    }

    @Column
    public String name;


}
