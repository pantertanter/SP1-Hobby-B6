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
    private PersonDetails.Gender gender;

    @Column(name = "age")
    private int age;

    @Column(name = "created", nullable = false)
    private LocalDate created;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Phone> phoneSet = new HashSet<>();

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;


    @Setter
    @MapsId
    @OneToOne(cascade = CascadeType.PERSIST)
    private Person person;
    public void addPhone(Phone phone){
        phoneSet.add(phone);
    }

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public PersonDetails(String email, PersonDetails.Gender gender, int age, LocalDate created, Set<Phone> phoneSet, Address address) {
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.created = created;
        this.phoneSet = phoneSet;
        this.address = address;
    }
}
