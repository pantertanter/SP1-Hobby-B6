package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "gender", unique = true, nullable = false)
    private Gender gender;

    @Column(name = "age", unique = true, nullable = false)
    private int age;

    @Column(name = "created", nullable = false)
    private LocalDate created;

    @OneToOne
    private PersonDetails personDetails;

    @ManyToOne
    private Profession profession;

    @ManyToMany
    private Set<Hobby> hobby = new HashSet<>();

    @ManyToMany
    private Set<Interests> interests = new HashSet<>();

    public enum Gender {
    MALE, FEMALE, OTHER
    }

}
