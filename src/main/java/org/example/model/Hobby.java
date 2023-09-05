package org.example.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "hobby")
@NamedQuery(name = "Hobby.findAll", query = "SELECT h FROM Hobby h")
public class Hobby {

    @Id
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    private Set<Person> persons = new HashSet<>();


}
