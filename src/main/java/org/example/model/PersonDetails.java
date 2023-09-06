package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "personDetails")
@NamedQuery(name = "PersonDetails.findAll", query = "SELECT p FROM PersonDetails p")
public class PersonDetails {
    @Id
    private int id;

    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @OneToMany
    private Set<Phone> phoneSet = new HashSet<>();

    @OneToOne
    private Address address;


    @Setter
    @MapsId
    @OneToOne
    private Person person;

    public PersonDetails(Address address) {
        this.address = address;
    }

    public void addPhone(Phone phone){
        phoneSet.add(phone);
    }
}
