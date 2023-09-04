package org.example.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
     */
    @Id
    private int id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "houseNumber", nullable = false)
    private String houseNumber;

    @ManyToOne
    private Zip zip;

    @MapsId
    @OneToOne
    private PersonDetails personDetails;



}
