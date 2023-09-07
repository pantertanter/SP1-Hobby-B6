package org.example.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "hobby")
@ToString
@NamedQuery(name = "Hobby.findAll", query = "SELECT h FROM Hobby h")
public class Hobby {

    @Id
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    private Set<Person> persons = new HashSet<>();

    public Hobby(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public Set<Person> getPersons() {
        return persons;
    }


}
