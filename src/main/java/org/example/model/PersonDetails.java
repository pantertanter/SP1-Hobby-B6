package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
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

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "gender", nullable = false)
    private Person.Gender gender;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "created", nullable = false)
    private LocalDate created;

    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @OneToMany
    private Set<Phone> phoneSet = new HashSet<>();

    @OneToOne
    private Address address;


    @Setter
    @MapsId
    @OneToOne
    private Person person;
    public void addPhone(Phone phone){
        phoneSet.add(phone);
    }

    public PersonDetails(String email, Person.Gender gender, int age, LocalDate created, Set<Phone> phoneSet, Address address) {
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.created = created;
        this.phoneSet = phoneSet;
        this.address = address;
    }
}
