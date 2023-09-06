package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "interest")
@NamedQuery(name = "Interest.findAll", query = "SELECT i FROM Interest i")
@NoArgsConstructor
public class Interest {

    @Setter
    @Id
    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @ManyToMany
    private Set<Person> persons = new HashSet<>();

    public Interest(String name) {
        this.name = name;
    }

}
