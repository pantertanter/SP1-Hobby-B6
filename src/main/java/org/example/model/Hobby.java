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
@Table(name = "hobby")
@NamedQuery(name = "Hobby.findAll", query = "SELECT h FROM Hobby h")
public class Hobby {

    @Setter
    @Id
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Setter
    @ManyToMany
    private Set<Person> persons = new HashSet<>();

    public Hobby(String name) {
        this.name = name;
    }


}
