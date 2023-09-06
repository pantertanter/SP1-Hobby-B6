package org.example.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "person")
@NamedQueries(@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"))
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    private PersonDetails personDetails;

    @ManyToOne
    private Profession profession;

    @ManyToMany
    private Set<Hobby> hobby = new HashSet<>();

    @ManyToMany
    private Set<Interest> interests = new HashSet<>();

    public Person(String name) {
        this.name = name;
    }

    public void setPersonDetails(PersonDetails personDetails) {
        this.personDetails = personDetails;
        if(personDetails != null){
            personDetails.setPerson(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
