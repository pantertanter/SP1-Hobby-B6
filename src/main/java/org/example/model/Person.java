package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "person")
@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @MapsId
    @OneToOne(cascade = CascadeType.PERSIST)
    private PersonDetails personDetails;

    @ManyToOne
    private Profession profession;

    @ManyToMany
    private Set<Hobby> hobby = new HashSet<>();

    @ManyToMany
    private Set<Interest> interests = new HashSet<>();

    public Person(String name, PersonDetails personDetails,
                  Profession profession, Set<Hobby> hobby,
                  Set<Interest> interests) {
        this.name = name;
        setPersonDetails(personDetails);
        this.profession = profession;
        this.hobby = hobby;
        this.interests = interests;
    }


    public Person(String name) {
        this.name = name;
    }


    public void setPersonDetails(PersonDetails personDetails) {
        this.personDetails = personDetails;
        if(personDetails != null){
            personDetails.setPerson(this);
        }
    }
}
