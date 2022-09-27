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

    @Column
    public String name;
  /*  public User() {
    //}



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/
}
