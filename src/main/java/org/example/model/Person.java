package org.example.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;
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

    @Column(name = "created", nullable = false)
    private LocalDate created;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PersonDetails personDetails;

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

    public PersonDetails getPersonDetails() {
        return personDetails;
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

    public Profession getProfession() {
        return profession;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personDetails= " + "Email: " + personDetails.getEmail() + " Gender: " + personDetails.getGender() + " Age: " + personDetails.getAge() + " Created: " + personDetails.getCreated() +
                '}';
    }
}
