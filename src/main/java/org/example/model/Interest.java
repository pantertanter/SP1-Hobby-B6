package org.example.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "interest")
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", unique = true, nullable = false)
    private String description;

    @ManyToMany
    private Set<Person> persons = new HashSet<>();

}
