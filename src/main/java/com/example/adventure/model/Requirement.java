package com.example.adventure.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Setter
@Getter
@ToString
@Table(name = "Requirements")
// Alt udkommenteret kode er til senere udvidelse af db
//@SecondaryTable(name = "Req_age", pkJoinColumns = @PrimaryKeyJoinColumn(name = "req_id"))
//@SecondaryTable(name = "Req_height", pkJoinColumns = @PrimaryKeyJoinColumn(name = "req_id"))
//@SecondaryTable(name = "Req_attendants", pkJoinColumns = @PrimaryKeyJoinColumn(name = "req_id"))
public class Requirement {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long Id;

    @Column//(name = "attendantsAmount", table = "Req_attendants")
    public int minimumAttendants;

    @Column//(name = "attendantsAmount", table = "Req_attendants")
    public int maximumAttendants;

    @Column
    public int alcoholLevel;

    @Column
    public int maxWeight;

    @Column//(name = "height", table = "Req_height")
    public int minimumHeight;

    @Column//(name = "height", table = "Req_height")
    public int maximumHeight;

    @Column//(name = "age", table = "Req_age")
    public int minimumAge;

    @Column//(name = "age", table = "Req_age")
    public int maximumAge;

    @Column
    public String requirementsDescrip;

    @OneToMany(mappedBy = "requirements")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Set<Activity> activities = new HashSet<>();


    public Requirement() {
        super();
    }
}
