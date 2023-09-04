package org.example.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "zip")
public class Zip {

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
     */

    //måske er det lidt meningsløst med en auto gen primary key når vi har unique zip også
    @Id
    @Column(name = "zipCode", unique = true, nullable = false)
    private int zipCode;

    @Column(name = "city", unique = true, nullable = false)
    private String city;

    @OneToMany
    private List<Address> addresses;
}
