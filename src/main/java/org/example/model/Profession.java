package org.example.model;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "profession")
@NamedQueries(@NamedQuery(name = "Profession.findAll", query = "SELECT p FROM Profession p"))
public class Profession {

    @Id
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany
    private Set<Person> persons = new HashSet<>();

    /*public enum Name {
        Bachelor, Candidate, Master, Doctor
    }*/

    public Profession(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
