package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "person")
@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created", nullable = false)
    private LocalDate created;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PersonDetails personDetails;

    @Setter
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Profession profession;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Hobby> hobbies = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Interest> interests = new HashSet<>();

    public Person(String name) {
        this.name = name;
    }

    @PrePersist
    public void prePersist() {
        created = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        created = LocalDate.now();
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
}
