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

    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @OneToOne
    @MapsId
    private PersonDetails personDetails;

    @ManyToOne
    private Profession profession;

    @ManyToMany
    private Set<Hobby> hobbies = new HashSet<>();

    @ManyToMany
    private Set<Interest> interests = new HashSet<>();

    public Person(String name) {
        this.name = name;
    }

    public void setPersonDetails(PersonDetails personDetails) {
        this.personDetails = personDetails;
        if (personDetails != null) {
            personDetails.setPerson(this);
        }
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
        if (hobbies != null) {
            for (Hobby h : hobbies) {
                h.getPersons().add(this); // Establish the bidirectional relationship
            }
        }
    }

    public void setInterests(Set<Interest> interests) {
        this.interests = interests;
        if (interests != null) {
            for (Interest i : interests) {
                i.getPersons().add(this); // Establish the bidirectional relationship
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
}
