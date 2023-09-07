package org.example.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "interest")
@NamedQuery(name = "Interest.findAll", query = "SELECT i FROM Interest i")
@NoArgsConstructor
@ToString
public class Interest {

    @Id
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    private Set<Person> persons = new HashSet<>();

    public Interest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getPersons() {
        return persons;
    }
}
