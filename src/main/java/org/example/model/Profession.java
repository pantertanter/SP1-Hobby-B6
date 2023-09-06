package org.example.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "profession")
@NamedQuery(name = "Profession.findAll", query = "SELECT p FROM Profession p")
public class Profession {

    @Setter
    @Id
    @Column(unique = true, nullable = false)
    private String name;

    @Setter
    private String description;

    @OneToMany
    private Set<Person> persons = new HashSet<>();

    /*public enum Name {
        Bachelor, Candidate, Master, Doctor
    }*/

    public Profession(String name) {
        this.name = name;
    }

}
