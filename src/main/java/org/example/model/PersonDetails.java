package org.example.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "personDetails")
@NamedQuery(name = "PersonDetails.findAll", query = "SELECT p FROM PersonDetails p")
public class PersonDetails {
    @Id
    private int id;

    @OneToMany
    private Set<Phone> phone = new HashSet<>();

    @OneToOne
    private Address address;

    @MapsId
    @OneToOne
    private Person person;
}
