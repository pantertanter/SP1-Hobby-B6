package org.example.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "personDetails")
public class PersonDetails {
    @Id
    private int id;

    private String phoneNumber;

    @OneToOne
    private Address address;

    @MapsId
    @OneToOne
    private Person person;

}
