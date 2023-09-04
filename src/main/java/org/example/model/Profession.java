package org.example.model;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "profession")
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private Name name;

    @Column(name = "description", unique = true, nullable = false)
    private String description;

    @OneToMany
    private Set<Person> persons = new HashSet<>();

    public enum Name {
        Bachelor, Candidate, Master, Doctor
    }
}
